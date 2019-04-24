package com.unicorn.sxmobileoa.app.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.widget.Button
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R

class CustomButton(context: Context, attrs: AttributeSet?) : Button(context, attrs) {

    init {
        initViews(context)
    }

    private fun initViews(context: Context){
        GradientDrawable().apply {
            val colorPrimary = ContextCompat.getColor(context, R.color.colorPrimary)
            setColor(colorPrimary)
            cornerRadius = ConvertUtils.dp2px(5f).toFloat()
        }.let { this.background = it }
        setTextColor(Color.WHITE)
        setTextSize(COMPLEX_UNIT_DIP,18f)
    }

}