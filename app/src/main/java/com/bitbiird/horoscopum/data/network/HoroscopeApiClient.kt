package com.bitbiird.horoscopum.data.network

import com.bitbiird.horoscopum.data.model.HoroscopeItem
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApiClient {
    @POST("/")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String
    ): Response<HoroscopeItem?>
}