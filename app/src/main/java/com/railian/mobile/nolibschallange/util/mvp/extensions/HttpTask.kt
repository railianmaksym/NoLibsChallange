package com.railian.mobile.nolibschallange.util.mvp.extensions

import android.os.AsyncTask

class HttpTask<T : Any>(
    private val request: () -> HttpResult<T>,
    private val onResponse: (HttpResult<T>) -> Unit
) : AsyncTask<Any, Any, HttpResult<T>>() {

    override fun doInBackground(vararg p0: Any?): HttpResult<T> {
        return request()
    }

    override fun onPostExecute(result: HttpResult<T>?) {
        super.onPostExecute(result)
        onResponse(result!!)
    }
}