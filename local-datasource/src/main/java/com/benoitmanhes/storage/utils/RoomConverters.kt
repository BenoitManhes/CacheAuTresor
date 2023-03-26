package com.benoitmanhes.storage.utils

import androidx.room.TypeConverter
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
}
