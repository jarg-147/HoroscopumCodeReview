package com.bitbiird.horoscopum.domain

import com.bitbiird.horoscopum.data.model.HoroscopeItem
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
    fun `when api returns null, if saved data is null, then return null`() = runBlocking {
        //Given
        coEvery { horoscopRepository.getHoroscopeData("", "") } returns null
        coEvery { horoscopRepository.getHoroscopeDataFromDB("", "") } returns null

        //When
        val response = getHoroscopeUseCase("", "")

        //Then
        coVerify { horoscopRepository.getHoroscopeData("", "") }
        coVerify { horoscopRepository.getHoroscopeDataFromDB("", "") }
        assert(response == null)
    }

    @Test
    fun `when api returns null, if saved data is not null, then saved data`() = runBlocking {
        //Given
        val horoscopeItem = mockk<HoroscopeItem>(relaxed = true)
        coEvery { horoscopRepository.getHoroscopeData("", "") } returns null
        coEvery { horoscopRepository.getHoroscopeDataFromDB("", "") } returns horoscopeItem

        //When
        val response = getHoroscopeUseCase("", "")

        //Then
        coVerify { horoscopRepository.getHoroscopeData("", "") }
        coVerify { horoscopRepository.getHoroscopeDataFromDB("", "") }
        assert(response == horoscopeItem)
    }

    @Test
    fun `when api returns value return value`() = runBlocking {
        //Given
        val horoscopeItem = mockk<HoroscopeItem>(relaxed = true)
        coEvery { horoscopRepository.getHoroscopeData("", "") } returns horoscopeItem

        //When
        val response = getHoroscopeUseCase("", "")

        //Then
        coVerify { horoscopRepository.getHoroscopeData("", "") }
        coVerify { horoscopRepository.clearHoroscopeDataFromDB("","") }
        coVerify { horoscopRepository.insertHoroscopeDataInDB(horoscopeItem,"","") }
        assert(response == horoscopeItem)
    }
}