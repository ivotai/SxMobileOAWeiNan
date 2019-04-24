package com.unicorn.sxmobileoa.n.ggxx.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.n.ggxx.model.GgxxDetail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.header_view_ggxx.view.*

@SuppressLint("ViewConstructor")
class GgxxHeaderView(context: Context, detail: GgxxDetail) : FrameLayout(context), LayoutContainer {

    override val containerView = this

    init {
        initViews(context, detail)
    }

    @SuppressLint("SetTextI18n")
    fun initViews(context: Context, detail: GgxxDetail) {
        LayoutInflater.from(context).inflate(R.layout.header_view_ggxx, this, true)
        detail.apply {
            tvTitle.text = title
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)
            tvSendUserName.text = "发布人:$sendUserName"
            tvCreateDate.text = createDate
        }
    }

}