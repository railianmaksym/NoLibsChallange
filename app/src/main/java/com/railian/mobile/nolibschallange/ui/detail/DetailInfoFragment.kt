package com.railian.mobile.nolibschallange.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.railian.mobile.nolibschallange.R
import com.railian.mobile.nolibschallange.data.pojo.SearchResult
import com.railian.mobile.nolibschallange.util.extensions.loadImageFromUrl
import kotlinx.android.synthetic.main.app_bar_title_only.*
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailInfoFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        @JvmStatic
        fun newInstance(result: SearchResult): DetailInfoFragment {
            return DetailInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("result", result)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.apply {
            navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
            setNavigationOnClickListener { activity?.onBackPressed() }
            title = "Detail info"
        }

        val result = arguments?.getParcelable<SearchResult>("result")

        result?.let {
            preview.loadImageFromUrl("http:${result.cover.large}")
            name.text = result.title
            artistTxt.text = result.mainArtist.name
            albumTxt.text = result.release.title
        }
    }

}