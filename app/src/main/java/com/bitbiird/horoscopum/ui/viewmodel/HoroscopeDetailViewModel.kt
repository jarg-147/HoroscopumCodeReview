package com.bitbiird.horoscopum.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitbiird.horoscopum.data.model.HoroscopeResponse
import com.bitbiird.horoscopum.data.state.ResponseState
import com.bitbiird.horoscopum.domain.GetHoroscopeUseCase
import com.bitbiird.horoscopum.utils.helpers.InternetConnectionHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase,
    private val internetConnectionHelper: InternetConnectionHelper
) : ViewModel() {

    val horoscopeModel = MutableLiveData<ResponseState>()
    val isLoading = MutableLiveData<Boolean>()

    private val days = arrayListOf(
        "yesterday",
        "today",
        "tomorrow"
    )

    fun onCreate(sign: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            if (internetConnectionHelper.isConnected()) {
                val response = withContext(Dispatchers.IO) {
                    getHoroscopeData(sign)
                }

                if (response.isNullOrEmpty() || response.any {it == null}) {
                    horoscopeModel.postValue(ResponseState.Error)
                } else {
                    horoscopeModel.postValue(ResponseState.Success(response))
                }
            } else {
                horoscopeModel.postValue(ResponseState.NoInternet)
            }

            isLoading.postValue(false)
        }
    }

    private suspend fun getHoroscopeData(sign: String): List<HoroscopeResponse?> {
        val horoscopeDaysData = arrayListOf<Deferred<HoroscopeResponse?>>()
        coroutineScope {
            for (day in days) {
                val horoscopeData = async {
                    getHoroscopeUseCase(sign, day)
                }
                horoscopeDaysData.add(horoscopeData)
            }
        }
        return horoscopeDaysData.awaitAll()
    }

}

