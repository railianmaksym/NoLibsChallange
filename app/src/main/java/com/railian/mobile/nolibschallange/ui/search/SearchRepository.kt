package com.railian.mobile.nolibschallange.ui.search

import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.data.pojo.TokenResponse
import com.railian.mobile.nolibschallange.data.pojo.network.HttpRequest
import com.railian.mobile.nolibschallange.util.extensions.HttpResult
import com.railian.mobile.nolibschallange.util.mvp.Repository
import org.json.JSONArray

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

    fun searchItems(query: String, token: String): HttpResult<List<SearchResult>> {
        val response = makeHttpRequest(
            HttpRequest(
                HttpRequest.RequestType.GET,
                "http://staging-gateway.mondiamedia.com/v2/api/sayt/flat?query=$query&limit=35",
                mapOf(
                    Pair("X-MM-GATEWAY-KEY", "Ge6c853cf-5593-a196-efdb-e3fd7b881eca"),
                    Pair("Authorization", "Bearer $token")
                )
            )
        )

        return if (response.isSuccess) {
            HttpResult.Success(unBoxSearchResults(response.body))
        } else {
            HttpResult.Error(IllegalArgumentException("Can't get results for this query"))
        }
    }

    private fun unBoxSearchResults(json: String): List<SearchResult> {
        val jsonArray = JSONArray(json)
        val list = mutableListOf<SearchResult>()

        try {
            for (i in 0 until jsonArray.length()) {
                list.add(SearchResult.unBox(jsonArray[i].toString()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }
}