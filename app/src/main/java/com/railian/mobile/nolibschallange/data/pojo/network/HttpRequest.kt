package com.railian.mobile.nolibschallange.data.pojo.network


data class HttpRequest(
    val type: RequestType,
    val url: String,
    val headers: Map<String, String>
) {
    enum class RequestType {
        GET,
        POST,
        PUT,
        UPDATE,
        DELETE
    }
}