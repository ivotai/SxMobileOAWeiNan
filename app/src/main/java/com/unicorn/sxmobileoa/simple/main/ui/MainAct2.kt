package com.unicorn.sxmobileoa.simple.main.ui

import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.AppUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.config.ConfigModule
import com.unicorn.sxmobileoa.app.mess.DialogUitls
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.login.ui.LoginAct
import com.unicorn.sxmobileoa.simple.main.Update
import com.unicorn.sxmobileoa.simple.main.network.loginout.LoginOut
import com.unicorn.sxmobileoa.simple.update.UpdateHelper
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.FileCallBack
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_main2.*
import me.majiajie.pagerbottomtabstrip.item.NormalItemView
import okhttp3.Call
import java.io.File

class MainAct2 : BaseAct() {

    override val layoutId = R.layout.act_main2

    override fun initViews() {
        initViewPager()
    }

    private fun initViewPager() {
        val navigationController = tab.custom()
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.home, R.mipmap.home_select, "首页") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.notice, R.mipmap.notice_select, "公告") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.news, R.mipmap.news_select, "新闻") })
                .addItem(NormalItemView(this).apply { initialize(R.mipmap.me, R.mipmap.me_select, "我的") })
                .build()
        navigationController.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun bindIntent() {
//       s()
        UpdateHelper().checkUpdate()
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(LoginOut::class.java, this, Consumer {
            LoginOut().toMaybe(this).subscribe { _ ->
                startActivity(Intent(this, LoginAct::class.java))
                finish()
            }
        })

        RxBus.get().registerEventOnMain(Update::class.java, this, Consumer {
            showUpdateDialog(it.apkUrl)
        })
    }

    private fun showUpdateDialog(apkUrl: String) {
        MaterialDialog.Builder(this)
                .title("版本更新")
                .cancelable(false)
                .positiveText("确认")
                .onPositive { _, _ ->
                    val fullUrl = "${ConfigModule.baseUrl}$apkUrl"
                    downloadApk(fullUrl)
                }
                .show()
    }

    private fun downloadApk(fullUrl: String) {
        val mask = DialogUitls.showMask2(this, "下载中...")
        OkHttpUtils
                .get()
                .url(fullUrl)
                .build()
                .execute(object : FileCallBack(ConfigModule.baseDir(), "SxMobileOA.apk") {
                    override fun onResponse(response: File, id: Int) {
                        mask.dismiss()
                        AppUtils.installApp(response)
                    }

                    override fun inProgress(progress: Float, total: Long, id: Int) {
                        mask.setProgress((progress * 100).toInt())
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                        mask.dismiss()
                    }
                })
    }


}
