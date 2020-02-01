package com.railian.mobile.nolibschallange.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.railian.mobile.nolibschallange.R

class SearchFragment : Fragment(R.layout.fragment_search), SearchView {

    private val presenter: SearchPresenter by lazy {
        SearchPresenter.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getToken()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun showError(id: Int) {
    }
}