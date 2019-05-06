package com.unicorn.sxmobileoa.simple.update

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.orhanobut.logger.Logger
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.simple.main.Update
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class UpdateHelper {

    fun checkUpdate() {
        val url = "http://${ConfigModule.ip}:${ConfigModule.updatePort}/appListener_r50/appUpdateCt/getNewApk?versNum=${AppUtils.getAppVersionName()}"
        val client = OkHttpClient()
        val request = Request.Builder().get().url(url).build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Logger.e("F")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val json = response.body()!!.string()
                    val updateResponse = ComponentHolder.appComponent.getGson().fromJson(json, UpdateResponse::class.java)
                    copeUpdateResponse(updateResponse)
                } catch (e: Exception) {
                    ToastUtils.showShort("更新异常")
                }
            }
        })
    }

    fun copeUpdateResponse(updateResponse: UpdateResponse) {
        if (!updateResponse.needUpdate) return
        RxBus.get().post(Update(updateResponse.apkUrl))
    }

}