package com.unicorn.sxmobileoa.n

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.unicorn.sxmobileoa.app.ui.BaseFra
import kotlinx.android.synthetic.main.fra_mail.*
import kotlinx.android.synthetic.main.my_fra.titleBar
import android.webkit.WebView
import android.net.http.SslError
import android.webkit.SslErrorHandler
import com.unicorn.sxmobileoa.app.Global

class MailFra : BaseFra() {

    override val layoutId = com.unicorn.sxmobileoa.R.layout.fra_mail

    override fun initViews() {
        titleBar.setTitle("邮箱", true)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true

        }
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val url = "http://113.200.190.227:8082/mailProject/appIndex/appLogin?userId=${Global.loginInfo!!.userId}"
        webView.loadUrl(url)
    }

    override fun bindIntent() {
    }


}