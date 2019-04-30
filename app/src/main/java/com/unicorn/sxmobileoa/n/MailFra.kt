package com.unicorn.sxmobileoa.n

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.unicorn.sxmobileoa.app.ui.BaseFra
import kotlinx.android.synthetic.main.fra_mail.*
import kotlinx.android.synthetic.main.my_fra.titleBar
import android.webkit.WebView
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebSettings.LOAD_NO_CACHE
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global


class MailFra : BaseFra() {

    override val layoutId = R.layout.fra_mail

    override fun initViews() {
        webView.settings.apply {
            javaScriptEnabled = true
            cacheMode = LOAD_NO_CACHE
        }
//        swipeRefreshLayout.setOnRefreshListener { webView.reload() }
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)

//        webView.webChromeClient= object :WebChromeClient()
//        {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                if(newProgress == 100){
//                    swipeRefreshLayout.isRefreshing = false
//                }
//            }
//

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                handler.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val url = "http://219.145.168.171:8585/mailProject/appIndex/appLogin?userId=${Global.loginInfo!!.userId}"
//        val url = "http://113.200.190.227:8082/mailProject/appIndex/appLogin?userId=${Global.loginInfo!!.userId}"
        webView.loadUrl(url)
    }

    override fun bindIntent() {
    }


}