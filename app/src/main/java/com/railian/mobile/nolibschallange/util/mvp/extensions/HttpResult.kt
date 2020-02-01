package com.railian.mobile.nolibschallange.util.mvp.extensions

sealed class HttpResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : HttpResult<T>()
    data class Error(val exception: Exception) : HttpResult<Nothing>()
}