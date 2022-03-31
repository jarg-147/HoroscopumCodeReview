package com.bitbiird.horoscopum.data.repository

import com.bitbiird.horoscopum.data.network.HoroscopeService
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeService) {

    suspend fun getHoroscopeData(sign: String, day: String) = api.getHoroscopeData(sign, day)

}