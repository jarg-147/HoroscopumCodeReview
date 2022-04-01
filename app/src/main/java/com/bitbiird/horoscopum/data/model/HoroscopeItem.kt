package com.bitbiird.horoscopum.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HoroscopeItem(
    @SerializedName("color")
    val color: String = "",
    @SerializedName("compatibility")
    var compatibility: String = "",
    @SerializedName("current_date")
    var currentDate: String = "",
    @SerializedName("date_range")
    val dateRange: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("lucky_number")
    var luckyNumber: String = "",
    @SerializedName("lucky_time")
    var luckyTime: String = "",
    @SerializedName("mood")
    val mood: String = "",
) : Serializable