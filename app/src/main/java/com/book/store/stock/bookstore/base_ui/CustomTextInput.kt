package com.book.store.stock.bookstore.base_ui

import android.content.Context
import android.util.AttributeSet
import com.book.store.stock.bookstore.utility.Fonts
import com.google.android.material.textfield.TextInputEditText

class CustomTextInput : TextInputEditText {
    private var typefaceIransans = Fonts.typefaceDefault(context)

    constructor(context: Context?) : super(context) {
        typeface = typefaceIransans
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        typeface = typefaceIransans
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        typeface = typefaceIransans
    }
}
