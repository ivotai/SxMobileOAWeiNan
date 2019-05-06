package com.unicorn.sxmobileoa.n

import android.webkit.WebViewClient
import com.unicorn.sxmobileoa.app.ui.BaseFra
import kotlinx.android.synthetic.main.fra_mail.*
import android.webkit.WebSettings.LOAD_NO_CACHE

import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.config.ConfigModule
import android.content.Intent
import android.net.Uri

class MailFra : BaseFra() {

    override val layoutId = com.unicorn.sxmobileoa.R.layout.fra_mail

    override fun initViews() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                cacheMode = LOAD_NO_CACHE
            }
            webViewClient = WebViewClient()
            val mailUrl = "http://${ConfigModule.ip}:${ConfigModule.mailPort}/mailProject/appIndex/appLogin?userId=${Global.loginInfo!!.userId}"
            loadUrl(mailUrl)
        }

        webView.setDownloadListener { url, userAgent, contentDisposition, mimeType, contentLength ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun bindIntent() {
    }

}