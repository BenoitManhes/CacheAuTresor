package com.benoitmanhes.storage.utils

import androidx.room.TypeConverter
import com.benoitmanhes.domain.model.CacheUserData
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
    fun listStringToString(list: List<String>): String =
        list.joinToString(LocalConstants.Room.listSeparator)

    @TypeConverter
    fun stringToListString(rawList: String): List<String> =
        rawList.split(LocalConstants.Room.listSeparator)

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
}
