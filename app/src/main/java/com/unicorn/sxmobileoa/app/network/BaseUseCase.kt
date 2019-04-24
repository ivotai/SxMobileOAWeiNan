package com.unicorn.sxmobileoa.app.network

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.common
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.network.model.Response
import com.unicorn.sxmobileoa.app.network.model.SimpleResponse
import com.unicorn.sxmobileoa.app.network.model.request
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.login.ui.LoginAct
import io.reactivex.Maybe
import okhttp3.MediaType
import okhttp3.RequestBody
import org.simpleframework.xml.core.Persister
import java.io.StringWriter

abstract class BaseUseCase<Result> {

    protected lateinit var request: request

    abstract fun toResult(json: String): Result

    fun toMaybe(lifecycleOwner: LifecycleOwner): Maybe<Result> {
        val requestXml = toXml(request)
        val requestBody = RequestBody.create(MediaType.parse("text/xml"), requestXml)
        return ComponentHolder.appComponent.getUniqueApi().post(Global.court?.dm ?: "", requestBody)
                .map {
                    return@map toSimpleResponse(it)
                }
                .filter { simpleResponse ->
                    val result = simpleResponse.code == Key.SUCCESS_CODE
                    if (!result) ToastUtils.showShort(simpleResponse.msg)
                    return@filter result
                }
                .filter {
                    if (it.message == 2) {
                        ToastUtils.showShort("登录超时")
                        val context = if(lifecycleOwner is BaseFra) lifecycleOwner.context else lifecycleOwner as Context
                        context!!.startActivity(Intent(context, LoginAct::class.java))
                    }
                    it.message != 2
                }
                .filter {
                    if (it.message == 0) {
                        ToastUtils.showShort(it.reMess)
                    }
                    return@filter it.message != 0
                }
                .map { it.result!! }
                .common(lifecycleOwner)
    }

    private fun toXml(source: Any) = StringWriter().apply { Persister().write(source, this) }.toString()

    private fun toSimpleResponse(response: Response) = SimpleResponse<Result>(response.code, response.msg).apply {
        response.parameters?.parameterList?.forEach { parameter ->
            when (parameter.name) {
                "key" -> Global.ticket = parameter.text
                "message" -> message = parameter.text.toInt()
                "reMess" -> reMess = parameter.text
            }
        }
        // message 成功
        if (message == 1) {
            response.parameters?.parameterList?.forEach { parameter ->
                when (parameter.name) {
                    "result" -> result = toResult(parameter.text)
                }
            }
        }
    }

}