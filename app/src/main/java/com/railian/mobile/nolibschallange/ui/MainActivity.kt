package com.railian.mobile.nolibschallange.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.railian.mobile.nolibschallange.R
import com.railian.mobile.nolibschallange.ui.search.SearchFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHost, SearchFragment())
                .addToBackStack(SearchFragment::class.java.simpleName)
                .commit()
    }
}
