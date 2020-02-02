package com.railian.mobile.nolibschallange.ui.search

import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.util.mvp.MVPView

interface SearchView : MVPView {
    fun openDetailScreen(result: SearchResult)

    fun showEmptyList()
}