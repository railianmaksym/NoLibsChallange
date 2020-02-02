package com.railian.mobile.nolibschallange.ui.search

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.railian.mobile.nolibschallange.R
import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.ui.detail.DetailInfoFragment
import com.railian.mobile.nolibschallange.util.extensions.hide
import com.railian.mobile.nolibschallange.util.extensions.makeClearableEditText
import com.railian.mobile.nolibschallange.util.extensions.show
import kotlinx.android.synthetic.main.app_bar_search.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search), SearchView {

    private val presenter: SearchPresenter by lazy {
        SearchPresenter.getInstance(activity?.getPreferences(MODE_PRIVATE)!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultsRV.adapter = presenter.searchResultsAdapter
        searchText.run {
            makeClearableEditText(
                clearDrawable = ContextCompat.getDrawable(
                    context!!,
                    R.drawable.ic_close_dark
                )!!
            )
            setOnEditorActionListener { _, _, _ ->
                if (searchText.text.trim().length < 2) {
                    showError(R.string.app_name)
                    return@setOnEditorActionListener false
                }
                presenter.searchItems(searchText.text.trim().toString())
                true
            }
        }
    }

    override fun openDetailScreen(result: SearchResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentHost, DetailInfoFragment.newInstance(result))
            ?.addToBackStack(DetailInfoFragment::class.java.simpleName)
            ?.commit()
    }

    override fun showProgress() {
        searchResultsRV.hide()
        progress.show()
    }

    override fun hideProgress() {
        progress.hide()
        searchResultsRV.show()
    }

    override fun showError(id: Int) {
    }
}