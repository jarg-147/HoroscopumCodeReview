package com.bitbiird.horoscopum.data.network

import com.bitbiird.horoscopum.data.model.HoroscopeItem
import javax.inject.Inject

class HoroscopeService @Inject constructor(private val api: HoroscopeApiClient) {

    suspend fun getHoroscopeData(sign: String, day: String): HoroscopeItem? {
        return try {
            api.getHoroscope(sign, day).body()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}