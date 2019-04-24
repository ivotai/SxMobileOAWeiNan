package com.unicorn.sxmobileoa.app.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.unicorn.sxmobileoa.R

class TitleBar(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    init {
        initViews(context)
    }

    private lateinit var tvTitle: TextView
    private lateinit var tvOperation: TextView
    private lateinit var ivBack: ImageView

    fun initViews(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.title_bar, this, true)
        tvTitle = findViewById(R.id.tvTitle)
        tvOperation = findViewById(R.id.tvOperation)
        ivBack = findViewById(R.id.ivBack)
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    fun setTitle(title: String, hideBack: Boolean) {
        tvTitle.text = title
        if (hideBack) ivBack.visibility = View.INVISIBLE
    }

    fun setOperation(operation: String): View {
        tvOperation.text = operation
        return tvOperation
    }

}