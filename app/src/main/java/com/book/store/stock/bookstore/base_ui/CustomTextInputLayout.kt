package com.book.store.stock.bookstore.base_ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.utility.Fonts
import com.google.android.material.textfield.TextInputLayout

class CustomTextInputLayout : TextInputLayout {
    private var typefaceIransans = Fonts.typefaceDefault(context)

    constructor(context: Context) : super(context) {
        typeface = typefaceIransans
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        typeface = typefaceIransans
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        typeface = typefaceIransans
    }


    override fun setError(errorText: CharSequence?) {
        super.setError(errorText)

        try {
            val layout = this;

            val errorView = (this.getChildAt(1) as ViewGroup).getChildAt(0).findViewById<AppCompatTextView>(
                R.id.textinput_error)

            (layout.getChildAt(1) as ViewGroup).layoutParams.width = LayoutParams.MATCH_PARENT
            (layout.getChildAt(1) as ViewGroup).getChildAt(0).layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT

            errorView.gravity = Gravity.RIGHT
            errorView.typeface = typefaceIransans

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


}
