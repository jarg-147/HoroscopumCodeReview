package com.bitbiird.horoscopum.utils.extensions

import com.bitbiird.horoscopum.data.db.entities.HoroscopeItemEntity
import com.bitbiird.horoscopum.data.model.HoroscopeItem

fun HoroscopeItem.toDatabaseModel(sign: String, day: String) = HoroscopeItemEntity(
    sign,
    day,
    color,
    compatibility,
    currentDate,
    dateRange,
    description,
    luckyNumber,
    luckyTime,
    mood
)

fun HoroscopeItemEntity.toModel() = HoroscopeItem(
    color, compatibility, currentDate, dateRange, description, luckyNumber, luckyTime, mood
)