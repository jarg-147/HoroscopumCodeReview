package com.bitbiird.horoscopum.domain

import com.bitbiird.horoscopum.data.model.HoroscopeResponse
import com.bitbiird.horoscopum.data.repository.HoroscopeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHoroscopeUseCaseTest {

    @RelaxedMockK
    private lateinit var horoscopRepository: HoroscopeRepository

    private lateinit var getHoroscopeUseCase: GetHoroscopeUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getHoroscopeUseCase = GetHoroscopeUseCase(horoscopRepository)
    }

    @Test
    fun `when api returns null then return null`() = runBlocking {
        //Given
        coEvery { horoscopRepository.getHoroscopeData("", "") } returns null

        //When
        val response = getHoroscopeUseCase("", "")

        //Then
        coVerify { horoscopRepository.getHoroscopeData("", "") }
        assert(response == null)
    }

    @Test
    fun `when api returns value return value`() = runBlocking {
        //Given
        val horoscopeResponse = mockk<HoroscopeResponse>(relaxed = true)
        coEvery { horoscopRepository.getHoroscopeData("", "") } returns horoscopeResponse

        //When
        val response = getHoroscopeUseCase("", "")

        //Then
        coVerify { horoscopRepository.getHoroscopeData("", "") }
        assert(response == horoscopeResponse)
    }
}