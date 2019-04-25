package com.unicorn.sxmobileoa.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import net.danlew.android.joda.JodaTimeAndroid
import java.net.SocketTimeoutException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
        setErrorHandler()
    }

    private fun init() {
        JodaTimeAndroid.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        Utils.init(this)
        Stetho.initializeWithDefaults(this)
    }

    private fun setErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            if (it is OnErrorNotImplementedException) {
                when (it.cause) {
                    is SocketTimeoutException -> ToastUtils.showShort("连接超时")
                    is JsonSyntaxException -> ToastUtils.showShort("解析异常")
                    else -> ToastUtils.showLong(it.cause.toString())
                }
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}