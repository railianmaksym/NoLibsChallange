package com.railian.mobile.nolibschallange.data.pojo

import org.json.JSONObject
import java.lang.Exception
import java.lang.IllegalArgumentException

data class TokenResponse(
    var accessToken: String = ""
) : PoJoClass {

    companion object {
        @JvmStatic
        @Throws(IllegalArgumentException::class)
        fun unBox(json: String): TokenResponse {
            return try {
                TokenResponse(
                    JSONObject(json).getString("accessToken")
                )
            }catch (e: Exception){
                throw IllegalArgumentException("")
            }
        }
    }
}