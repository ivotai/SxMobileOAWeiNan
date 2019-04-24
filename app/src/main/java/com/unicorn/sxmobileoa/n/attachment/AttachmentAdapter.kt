package com.unicorn.sxmobileoa.n.attachment

import com.blankj.utilcode.util.FileUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.mess.DialogUitls
import com.unicorn.sxmobileoa.app.mess.FileUtils2
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.spd.model.Fj
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import kotlinx.android.synthetic.main.item_fj.*
import okhttp3.Call
import java.io.File

class AttachmentAdapter : BaseQuickAdapter<Fj, MyHolder>(R.layout.item_fj) {

    override fun convert(helper: MyHolder, item: Fj) {
        helper.apply {
            tvText.text = item.fjmc
            val extension = FileUtils.getFileExtension(item.fjmc)
            when (extension) {
                in listOf("doc", "docx") -> Glide.with(mContext).load(R.mipmap.word).into(ivImage)
                in listOf("ppt", "pptx") -> Glide.with(mContext).load(R.mipmap.ppt).into(ivImage)
                in listOf("xls", "xlsx") -> Glide.with(mContext).load(R.mipmap.excel).into(ivImage)
                in listOf("jpg", "jpeg", "png") -> Glide.with(mContext).load(R.mipmap.img).into(ivImage)
            }

            root.safeClicks().subscribe {
                val fullUrl = item.fjdz
                val lastIndex = fullUrl.lastIndexOf("/")
                val fileName = fullUrl.substring(lastIndex + 1, fullUrl.length)
                val file = File(ConfigModule.baseDir(), fileName)
//                if (file.exists()) {
//                    FileUtils2.openFile(mContext, file = file)
//                } else {
                val mask = DialogUitls.showMask(mContext, "下载附件中...")
                OkHttpUtils
                        .get()
                        .url(fullUrl)
                        .build()
                        .execute(object : FileCallBack(ConfigModule.baseDir(), fileName) {
                            override fun onResponse(response: File, id: Int) {
                                mask.dismiss()
                                FileUtils2.openFile(mContext, file = response)
                            }

                            override fun onError(call: Call?, e: Exception?, id: Int) {
                                mask.dismiss()
                            }
                        })
//                }
            }
        }
    }

}