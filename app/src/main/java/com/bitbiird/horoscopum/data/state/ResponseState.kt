package com.bitbiird.horoscopum.data.state

sealed class ResponseState {
    data class Success(val data: Any) : ResponseState()
    object Error : ResponseState()
    object NoInternet : ResponseState()
}