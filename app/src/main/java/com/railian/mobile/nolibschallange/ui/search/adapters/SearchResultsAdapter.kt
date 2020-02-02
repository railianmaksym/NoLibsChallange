package com.railian.mobile.nolibschallange.ui.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.railian.mobile.nolibschallange.R
import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.ui.search.SearchPresenter
import com.railian.mobile.nolibschallange.ui.search.SearchView
import com.railian.mobile.nolibschallange.util.extensions.loadImageFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.app_bar_search.*
import kotlinx.android.synthetic.main.item_search_list.*

class SearchResultsAdapter(private val presenter: SearchPresenter) :
    RecyclerView.Adapter<SearchResultViewHolder>() {

    var results: List<SearchResult> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchResultViewHolder(inflater.inflate(R.layout.item_search_list, parent, false))
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val result = results[position]

        with(holder) {
            title.text = result.title
            artist.text = result.mainArtist.name
            preview.loadImageFromUrl("http:${result.cover.small}")
            type.setImageResource(
                if (result.type == "album")
                    R.drawable.ic_album
                else
                    R.drawable.ic_song
            )
            resultLayout.setOnClickListener {
                presenter.openDetailScreen(result)
            }
        }
    }

}

class SearchResultViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer
