package com.bitbiird.horoscopum.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "horoscope_item_table", primaryKeys = ["sign", "day"])
data class HoroscopeItemEntity(
    @ColumnInfo(name = "sign")
    val sign: String = "",
    @ColumnInfo(name = "day")
    val day: String = "",
    @ColumnInfo(name = "color")
    val color: String = "",
    @ColumnInfo(name = "compatibility")
    var compatibility: String = "",
    @ColumnInfo(name = "current_date")
    var currentDate: String = "",
    @ColumnInfo(name = "date_range")
    val dateRange: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "lucky_number")
    var luckyNumber: String = "",
    @ColumnInfo(name = "lucky_time")
    var luckyTime: String = "",
    @ColumnInfo(name = "mood")
    val mood: String = "",
) : Serializable
