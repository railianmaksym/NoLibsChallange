package com.railian.mobile.nolibschallange.util.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.railian.mobile.nolibschallange.util.extensions.HttpTask

abstract class Presenter<T : MVPView> : LifecycleObserver {
    abstract var view: T?
    protected val tasks: MutableList<HttpTask<out Any>> = mutableListOf()

    fun bindView(view: T) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun unbindView() {
        view = null
        tasks.forEach { it.cancel(false) }
    }
}