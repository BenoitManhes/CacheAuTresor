package com.benoitmanhes.storage.utils

import androidx.room.TypeConverter
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.DraftCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

object RoomConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun listStringNullableToString(list: List<String>?): String? =
        list?.let(Gson()::toJson)

    @TypeConverter
    fun stringToListStringNullable(rawList: String?): List<String>? = rawList?.let {
        val listType = object : TypeToken<List<String>>() {}.type
        Gson().fromJson(rawList, listType)
    }

    @TypeConverter
    fun mapStringIntToString(map: Map<String, Int>): String =
        Gson().toJson(map)

    @TypeConverter
    fun stringToMapStringInt(rawList: String): Map<String, Int> {
        val listType = object : TypeToken<Map<String, Int>>() {}.type
        return Gson().fromJson(rawList, listType)
    }

    @TypeConverter
    fun fromCacheUserDataMarkerList(list: List<CacheUserData.Marker>): String {
        val gson = Gson()
        val type = object : TypeToken<List<CacheUserData.Marker>>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toCacheUserDataMarkerList(value: String): List<CacheUserData.Marker> {
        val gson = Gson()
        val type = object : TypeToken<List<CacheUserData.Marker>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCacheType(cacheType: Cache.Type): String {
        val gson = Gson()
        val type = when (cacheType) {
            Cache.Type.Classical -> object : TypeToken<Cache.Type.Classical>() {}.type
            is Cache.Type.Piste -> object : TypeToken<Cache.Type.Piste>() {}.type
            is Cache.Type.Mystery -> object : TypeToken<Cache.Type.Mystery>() {}.type
            is Cache.Type.Coop -> object : TypeToken<Cache.Type.Coop>() {}.type
        }
        val wrapperType = object : TypeToken<Wrapper>() {}.type
        val wrapper = Wrapper(cacheType::class.java.simpleName, gson.toJson(cacheType, type))
        return gson.toJson(wrapper, wrapperType)
    }

    @TypeConverter
    fun toCacheType(json: String): Cache.Type {
        val wrapperType = object : TypeToken<Wrapper>() {}.type
        val wrapper = Gson().fromJson<Wrapper>(json, wrapperType)
        return when (wrapper.type) {
            Cache.Type.Classical::class.java.simpleName -> Gson().fromJson(
                wrapper.rawData,
                Cache.Type.Classical::class.java
            )

            Cache.Type.Coop::class.java.simpleName -> Gson().fromJson(wrapper.rawData, Cache.Type.Coop::class.java)
            Cache.Type.Mystery::class.java.simpleName -> Gson().fromJson(
                wrapper.rawData,
                Cache.Type.Mystery::class.java
            )

            Cache.Type.Piste::class.java.simpleName -> Gson().fromJson(wrapper.rawData, Cache.Type.Piste::class.java)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }

    @TypeConverter
    fun fromDraftCacheType(cacheType: DraftCache.Type?): String? = cacheType?.let {
        val gson = Gson()
        val type = when (cacheType) {
            DraftCache.Type.Classical -> object : TypeToken<DraftCache.Type.Classical>() {}.type
            is DraftCache.Type.Piste -> object : TypeToken<DraftCache.Type.Piste>() {}.type
            is DraftCache.Type.Mystery -> object : TypeToken<DraftCache.Type.Mystery>() {}.type
            is DraftCache.Type.Coop -> object : TypeToken<DraftCache.Type.Coop>() {}.type
        }
        val wrapperType = object : TypeToken<Wrapper>() {}.type
        val wrapper = Wrapper(cacheType::class.java.simpleName, gson.toJson(cacheType, type))
        gson.toJson(wrapper, wrapperType)
    }

    @TypeConverter
    fun toDraftCacheType(json: String?): DraftCache.Type? = json?.let {
        val wrapperType = object : TypeToken<Wrapper>() {}.type
        val wrapper = Gson().fromJson<Wrapper>(json, wrapperType)
        when (wrapper.type) {
            DraftCache.Type.Classical::class.java.simpleName -> Gson().fromJson(
                wrapper.rawData,
                DraftCache.Type.Classical::class.java
            )

            DraftCache.Type.Coop::class.java.simpleName -> Gson().fromJson(
                wrapper.rawData,
                DraftCache.Type.Coop::class.java
            )

            DraftCache.Type.Mystery::class.java.simpleName ->
                Gson().fromJson(wrapper.rawData, DraftCache.Type.Mystery::class.java)

            DraftCache.Type.Piste::class.java.simpleName -> Gson().fromJson(
                wrapper.rawData,
                DraftCache.Type.Piste::class.java
            )

            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}

data class Wrapper(val type: String, val rawData: String)
