package com.unicorn.sxmobileoa.spd.ui.headerView

import android.annotation.SuppressLint
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.mess.DialogUitls
import com.unicorn.sxmobileoa.app.mess.FileUtils2
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.attachment.AttachmentAct
import com.unicorn.sxmobileoa.n.process.ui.ProcessAct
import com.unicorn.sxmobileoa.spd.model.Spd
import com.unicorn.sxmobileoa.spd.network.spdZw.SpdZw
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import okhttp3.Call
import java.io.File


@SuppressLint("CheckResult", "ViewConstructor")
class OperationHeaderView(context: Context, val spd: Spd) : FrameLayout(context) {

    init {
        initViews(context)
    }

    fun initViews(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.header_view_operation, this, true)
        llZhengWen = view.findViewById(R.id.llZhengWen)
        llZhengWen.safeClicks().subscribe { _ ->
            SpdZw(spd.spdXx.id).toMaybe(context as LifecycleOwner).subscribe {
                if (TextUtils.isEmpty(it.wjdz)) {
                    ToastUtils.showShort("无正文")
                    return@subscribe
                }

//                val fullUrl = "${ConfigModule.baseAttachmentUrl}?fybm=${Global.court!!.dm}&url=${it.wjdz}"
                val fullUrl = it.wjdz
                val lastIndex = fullUrl.lastIndexOf("/")
                val fileName = fullUrl.substring(lastIndex + 1, fullUrl.length)
                val file = File(ConfigModule.baseDir(), fileName)
                if (file.exists()) {
                    FileUtils2.openFile(context, file = file)
                } else {
                    val mask = DialogUitls.showMask(context = context, title = "下载正文中...")
                    OkHttpUtils
                            .get()
                            .url(fullUrl)
                            .build()
                            .execute(object : FileCallBack(ConfigModule.baseDir(), fileName) {
                                override fun onResponse(response: File, id: Int) {
                                    FileUtils2.openFile(context, file = response)
                                    mask.dismiss()
                                }

                                override fun onError(call: Call?, e: Exception?, id: Int) {
                                    mask.dismiss()
                                }
                            })
                }
            }
        }

        val llAttachment = findViewById<LinearLayout>(R.id.llAttachment)
        llAttachment.safeClicks().subscribe { _ ->
            if (spd.spdFj.isEmpty()) {
                ToastUtils.showShort("无附件")
                return@subscribe
            }

            context.startActivity(Intent(context, AttachmentAct::class.java).apply {
                putExtra(Key.spd, spd)
            })
        }

        val llLiuCheng = findViewById<LinearLayout>(R.id.llLiuCheng)
        llLiuCheng.safeClicks().subscribe {
            context.startActivity(Intent(context, ProcessAct::class.java).apply {
                putExtra(Key.processInstanceId, spd.spdXx.processInstancesId)
            })
        }
    }

    lateinit var llZhengWen: LinearLayout

}