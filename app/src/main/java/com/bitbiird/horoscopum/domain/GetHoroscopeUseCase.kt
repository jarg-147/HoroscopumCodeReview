package com.bitbiird.horoscopum.domain

import com.bitbiird.horoscopum.data.repository.HoroscopeRepository
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {
    suspend operator fun invoke(
        sign: String,
        day: String
    ) = repository.getHoroscopeData(sign, day)
}