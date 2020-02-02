package com.railian.mobile.nolibschallange.ui.search

import android.content.SharedPreferences
import com.railian.mobile.nolibschallange.R
import com.railian.mobile.nolibschallange.data.local.TokenStore
import com.railian.mobile.nolibschallange.data.network.HttpResult
import com.railian.mobile.nolibschallange.data.network.HttpTask
import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.data.pojo.TokenResponse
import com.railian.mobile.nolibschallange.ui.search.adapters.SearchResultsAdapter
import com.railian.mobile.nolibschallange.util.mvp.Presenter

class SearchPresenter(preferences: SharedPreferences) : Presenter<SearchView>() {

    override var view: SearchView? = null
    private val repository = SearchRepository()
    private val tokenStore = TokenStore(preferences)

    val searchResultsAdapter = SearchResultsAdapter(this)
    var currentQuery: String = ""

    companion object {
        private var instance: SearchPresenter? = null
        fun getInstance(preferences: SharedPreferences): SearchPresenter {
            if (instance == null) instance = SearchPresenter(preferences)
            return instance!!
        }
    }


    fun searchItems(query: String = currentQuery) {
        view?.showProgress()
        currentQuery = query

        if (tokenStore.token.isEmpty()) {
            getToken()
        } else {
            val searchTask =
                HttpTask(
                    { repository.searchItems(currentQuery, tokenStore.token) },
                    {
                        when (it) {
                            is HttpResult.Success -> onSuccessGetSearchResults(it.data)
                            is HttpResult.Error -> onErrorGetToken(it.exception)
                        }
                    }
                )

            searchTask.execute()

            tasks.add(searchTask)
        }

    }

    private fun onSuccessGetSearchResults(data: List<SearchResult>) {
        view?.hideProgress()

        if (data.isEmpty())
            view?.showEmptyList()
        else
            searchResultsAdapter.results = data
    }

    private fun getToken() {

        val searchTask =
            HttpTask(
                { repository.getToken() },
                {
                    when (it) {
                        is HttpResult.Success -> onSuccessGetToken(it.data)
                        is HttpResult.Error -> onErrorGetToken(it.exception)
                    }
                }
            )

        searchTask.execute()
        tasks.add(searchTask)
    }

    private fun onSuccessGetToken(data: TokenResponse) {
        tokenStore.token = data.accessToken
        searchItems(currentQuery)
    }

    private fun onErrorGetToken(exception: Exception) {
        view?.hideProgress()
        view?.showError(R.string.network_error)
    }

    fun openDetailScreen(result: SearchResult) {
        view?.openDetailScreen(result)
    }

}