package com.railian.mobile.nolibschallange.ui.search

import com.railian.mobile.nolibschallange.data.pojo.TokenResponse
import com.railian.mobile.nolibschallange.util.mvp.Presenter
import com.railian.mobile.nolibschallange.util.mvp.extensions.HttpResult
import com.railian.mobile.nolibschallange.util.mvp.extensions.HttpTask

class SearchPresenter : Presenter<SearchView>() {

    private val repository = SearchRepository()

    companion object {
        private var instance: SearchPresenter? = null
        fun getInstance(): SearchPresenter {
            if (instance == null) instance = SearchPresenter()
            return instance!!
        }
    }

    override var view: SearchView? = null

    fun getToken() {
        view?.showProgress()

        val searchTask = HttpTask(
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
        println()
    }

    private fun onErrorGetToken(exception: Exception) {
        println()
    }

}