package com.railian.mobile.nolibschallange.util.extensions

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.railian.mobile.nolibschallange.R
import java.net.HttpURLConnection
import java.net.URL

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun EditText.makeClearableEditText(
    onClear: (() -> Unit)? = null,
    clearDrawable: Drawable
) {
    val updateRightDrawable = {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null, null,
            if (text.isNotEmpty()) clearDrawable else null,
            null
        )
    }
    updateRightDrawable()

    this.afterTextChanged {
        updateRightDrawable()
    }
    this.onRightDrawableClicked {
        this.text.clear()
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        onClear?.invoke()
        this.requestFocus()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

fun ImageView.loadImageFromUrl(strURL: String) {
    setImageDrawable(ColorDrawable(context.resources.getColor(R.color.white)))

    ImageTask(strURL) {
        if (it == null)
            setImageResource(R.drawable.ic_cassette)
        else
            setImageBitmap(it)
    }.execute()
}

class ImageTask(
    private val strURL: String,
    private val onLoadImage: (Bitmap?) -> Unit
) : AsyncTask<String, Any, Bitmap?>() {
    override fun doInBackground(vararg p0: String?): Bitmap? {
        val url = URL(strURL)
        val httpConn: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpConn.requestMethod = "GET"
        var bitmap: Bitmap? = null

        try {
            httpConn.connect()
            if (httpConn.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = httpConn.inputStream
                val bmOptions: BitmapFactory.Options = BitmapFactory.Options()
                bmOptions.inSampleSize = 1
                bitmap = BitmapFactory.decodeStream(inputStream, null, bmOptions)
            }

        } catch (e: Exception) {
            bitmap = null
        } finally {
            httpConn.disconnect()
        }

        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        onLoadImage(result)
    }
}