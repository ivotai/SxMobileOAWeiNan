package com.unicorn.sxmobileoa.n.news.ui

import android.annotation.SuppressLint
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.news.network.GetDetail
import kotlinx.android.synthetic.main.act_news_detail.*


class NewsDetailAct : BaseAct() {

    override val layoutId = R.layout.act_news_detail

    override fun initViews() {
        titleBar.setTitle("新闻详情")
    }

    override fun bindIntent() {
        getDetail()
    }

    @SuppressLint("CheckResult", "SetJavaScriptEnabled")
    private fun getDetail() {
        val contentId = intent.getStringExtra(Key.contentId)
//        val url = "${ConfigModule.baseUrl}XwViewServlet.do?fybm=${Global.court!!.dm}&contentid=$contentId"
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }
        }
        webView.settings.javaScriptEnabled = true
//        webView.loadUrl(url)

        GetDetail(contentId).toMaybe(this).subscribe {
//
//            webView.loadUrl(url)
//
            webView.loadDataWithBaseURL(null, it.txt, "text/html", "utf-8", null)
        }
    }



}
