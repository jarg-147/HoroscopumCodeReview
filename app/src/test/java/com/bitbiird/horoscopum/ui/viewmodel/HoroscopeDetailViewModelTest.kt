package com.bitbiird.horoscopum.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.data.state.ResponseState
import com.bitbiird.horoscopum.domain.GetHoroscopeUseCase
import com.bitbiird.horoscopum.utils.helpers.InternetConnectionHelper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HoroscopeDetailViewModelTest {

    @RelaxedMockK
    private lateinit var getHoroscopeUseCase: GetHoroscopeUseCase

    @RelaxedMockK
    private lateinit var internetConnectionHelper: InternetConnectionHelper

    private lateinit var viewModel: HoroscopeDetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel =
            HoroscopeDetailViewModel(
                getHoroscopeUseCase,
                internetConnectionHelper
            )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel request horoscope data, if there's no internet return no internet error`() =
        runTest {
            //Given
            coEvery { internetConnectionHelper.isConnected() } returns false

            //When
            viewModel.onCreate("")

            //Then
            viewModel.horoscopeModel.observeForever {
                assert(it == ResponseState.NoInternet)
            }

        }

    @Test
    fun `when viewmodel request horoscope data, if horoscope data is null return error`() =
        runTest {
            //Given
            coEvery { internetConnectionHelper.isConnected() } returns true
            coEvery { getHoroscopeUseCase("", "") } returns null

            //When
            viewModel.onCreate("")

            //Then
            viewModel.horoscopeModel.observeForever {
                assert(it == ResponseState.Error)
            }
        }

    @Test
    fun `when viewmodel request horoscope data, return value`() = runTest {
        //Given
        val response = mockk<HoroscopeItem>(relaxed = true)
        coEvery { internetConnectionHelper.isConnected() } returns true
        coEvery { getHoroscopeUseCase("", "") } returns response

        //When
        viewModel.onCreate("")

        //Then
        viewModel.horoscopeModel.observeForever {
            assert(
                it == ResponseState.Success(
                    response
                )
            )
        }

    }
}