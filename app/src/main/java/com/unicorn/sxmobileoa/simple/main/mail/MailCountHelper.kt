package com.unicorn.sxmobileoa.simple.main.mail

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.mess.RxBus
import florent37.github.com.rxlifecycle.RxLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class MailCountHelper {

    @SuppressLint("CheckResult")
    fun getMailCountIncessantly(lifecycleOwner: LifecycleOwner) {
        Observable.interval(30, TimeUnit.SECONDS)
                .compose(RxLifecycle.with(lifecycleOwner).disposeOnDestroy())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getMailCount()
                }
    }

    private fun getMailCount() {
        val url = "http://${ConfigModule.ip}:${ConfigModule.mailPort}/mailProject/webInterfacesController/getUserMailCt?userId=${Global.loginInfo!!.userId}"
        val client = OkHttpClient()
        val request = Request.Builder().get().url(url).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val str = response.body()!!.string()
                    val count = str.toInt()
                    RxBus.get().post(MailCountEvent(count))
                } catch (e: Exception) {

                }
            }
        })
    }

}