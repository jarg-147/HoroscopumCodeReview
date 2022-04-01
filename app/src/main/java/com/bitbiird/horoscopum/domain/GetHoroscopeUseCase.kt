package com.bitbiird.horoscopum.domain

import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.data.repository.HoroscopeRepository
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {
    suspend operator fun invoke(
        sign: String,
        day: String
    ): HoroscopeItem? {
        val response = repository.getHoroscopeData(sign, day)

        return if (response != null) {
            repository.clearHoroscopeDataFromDB(sign, day)
            repository.insertHoroscopeDataInDB(response, sign, day)
            response
        } else {
            repository.getHoroscopeDataFromDB(sign, day)
        }
    }
}