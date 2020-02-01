package com.railian.mobile.nolibschallange.ui.search

import com.railian.mobile.nolibschallange.data.pojo.TokenResponse
import com.railian.mobile.nolibschallange.data.pojo.network.HttpRequest
import com.railian.mobile.nolibschallange.util.mvp.Repository
import com.railian.mobile.nolibschallange.util.mvp.extensions.HttpResult

class SearchRepository : Repository() {

    fun getToken(): HttpResult<TokenResponse> {
        val response = makeHttpRequest(
            HttpRequest(
                url = "http://staging-gateway.mondiamedia.com/v0/api/gateway/token/client",
                type = HttpRequest.RequestType.POST,
                headers = mapOf(Pair("X-MM-GATEWAY-KEY", "Ge6c853cf-5593-a196-efdb-e3fd7b881eca"))
            )
        )

        return if (response.isSuccess) {
            HttpResult.Success(TokenResponse.unBox(response.body))
        } else {
            HttpResult.Error(IllegalArgumentException("Can't get token for this gateway key"))
        }
    }
}