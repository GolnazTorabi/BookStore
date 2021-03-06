package com.book.store.stock.bookstore.utility

import android.content.Context
import android.graphics.Typeface

object Fonts {
    private var typefaceDefault: Typeface? = null
    private var typefaceBold: Typeface? = null
    fun typefaceDefault(mContext: Context?): Typeface? {
        if (typefaceDefault == null) {
            typefaceDefault =
                Typeface.createFromAsset(mContext?.assets, "font/iransans.ttf")
        }
        return typefaceDefault
    }


    fun typefaceBold(mContext: Context): Typeface? {
        if (typefaceBold == null) {
            typefaceBold = Typeface.createFromAsset(
                mContext.assets,
                "font/iransans_bold.ttf"
            )
        }
        return typefaceBold
    }

}
