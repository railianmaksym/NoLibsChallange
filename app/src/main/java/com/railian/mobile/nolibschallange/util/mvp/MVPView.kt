package com.railian.mobile.nolibschallange.util.mvp

import androidx.annotation.StringRes

interface MVPView {
    fun showProgress()

    fun hideProgress()

    fun showError(@StringRes id: Int)
}