package com.unicorn.sxmobileoa.app.mess

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key

class KeywordHeaderView(context: Context) : FrameLayout(context) {

    init {
        initViews(context)
    }

    lateinit var etKeyword: TextView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.header_view_keyword, this, true)

        etKeyword = findViewById(R.id.etKeyword)

        GradientDrawable().apply {
            setColor(ContextCompat.getColor(context, R.color.md_grey_100))
            cornerRadius = ConvertUtils.dp2px(Key.cornerRadiusDp).toFloat()
        }.let { etKeyword.background = it }
    }

    fun setHint(hint:String){
        etKeyword.hint = hint
    }

}