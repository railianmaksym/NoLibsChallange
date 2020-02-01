package com.railian.mobile.nolibschallange.util.mvp

import com.railian.mobile.nolibschallange.data.pojo.network.HttpRequest
import com.railian.mobile.nolibschallange.util.mvp.extensions.HttpResponse
import java.net.HttpURLConnection
import java.net.URL

abstract class Repository {
    fun makeHttpRequest(request: HttpRequest): HttpResponse {

        val connection = (
                URL(request.url)
                    .openConnection() as HttpURLConnection
                ).apply {

            requestMethod = request.type.name
            setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            setRequestProperty("Accept", "application/json")
            doInput = true
            doOutput = true

            // Add custom headers
            for ((key, value) in request.headers) {
                setRequestProperty(key, value)
            }
        }

        return try {
            connection.run {
                connect()
                inputStream.bufferedReader().use { reader ->
                    HttpResponse(connection.responseCode == 200, reader.readText())
                }
            }
        } catch (e: java.lang.Exception) {
            HttpResponse(isSuccess = false)
        } finally {
            connection.disconnect()
        }
    }
}