package com.book.store.stock.bookstore.base_ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.book.store.stock.bookstore.utility.Fonts

@SuppressLint("AppCompatCustomView")
class CustomEditText : EditText {
    private var typefaceIransans = Fonts.typefaceDefault(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        typeface = typefaceIransans
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        typeface = typefaceIransans
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        typeface = typefaceIransans
    }

    constructor(context: Context?) : super(context) {
        typeface = typefaceIransans
    }
}

