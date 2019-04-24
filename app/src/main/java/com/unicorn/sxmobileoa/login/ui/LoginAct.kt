package com.unicorn.sxmobileoa.login.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import com.afollestad.materialdialogs.MaterialDialog
import com.github.florent37.rxsharedpreferences.RxSharedPreferences
import com.jakewharton.rxbinding2.widget.RxTextView
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.*
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.login.model.LoginUser
import com.unicorn.sxmobileoa.login.network.Login
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.simple.court.ui.CourtAct
import com.unicorn.sxmobileoa.simple.main.ui.MainAct2
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.act_login.*

@SuppressLint("CheckResult")
class LoginAct : BaseAct() {

    override val layoutId = R.layout.act_login

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT != 0) {
            finish()
            return
        }


//        etPassword.setText("withub4l")
//        etPassword.setText("zyadmin")
    }

    override fun bindIntent() {
        observeInput()
        tvCourt.safeClicks().subscribe { startActivity(Intent(this@LoginAct, CourtAct::class.java)) }
        btnLogin.safeClicks().subscribe {
            login()
        }
        restoreInputInfo()
    }

    private fun showListDialog() {
        val list = listOf(
                LoginUser("系统管理员", "0000", "admin")
//                LoginUser("杨继锋", "0704", "admin"),
//                LoginUser("周晓平", "0115", "admin"),
//                LoginUser("郭建军", "0902", "admin"),
//                LoginUser("杨明德", "yangmd", "admin"),
//                LoginUser("覃亮", "qinliang", "0000")
        )
        MaterialDialog.Builder(this)
                .items(list.map { it.name })
                .itemsCallback { _, _, position, _ ->
                    val loginUser = list[position]
                    etUsername.setText(loginUser.loginName)
                    etPassword.setText(loginUser.password)
                    login()
                }.show()
    }

    private fun login() {
        Login(etUsername.trimText(), etPassword.trimText()).toMaybe(this).subscribe { loginInfo ->
            Global.loginInfo = loginInfo
            saveInputInfo()
            startActivityAndFinish(Intent(this@LoginAct, MainAct2::class.java))
        }
    }


    private fun observeInput() {
        Observable.combineLatest(
                RxTextView.textChanges(tvCourt).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etUsername).map { !TextUtils.isEmpty(it) },
                RxTextView.textChanges(etPassword).map { !TextUtils.isEmpty(it) },
                Function3<Boolean, Boolean, Boolean, Boolean> { court, username, password -> return@Function3 court && username && password })
                .subscribe { btnLogin.isEnabled = it }
    }

    private fun restoreInputInfo() {
        RxSharedPreferences.with(this).apply {
            getString(Key.courtStr, "")
                    .subscribe { courtStr ->
                        if (courtStr == "") return@subscribe
                        val court = ComponentHolder.appComponent.getGson().fromJson(courtStr, Court::class.java)
                        Global.court = court
                        tvCourt.text = court.dmms
                    }
            getString(Key.username, "").subscribe { etUsername.setText(it) }
        }
    }

    private fun saveInputInfo() {
        RxSharedPreferences.with(this).apply {
            putString(Key.courtStr, ComponentHolder.appComponent.getGson().toJson(Global.court)).subscribe { }
            putString(Key.username, etUsername.trimText()).subscribe { }
        }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(Court::class.java, this, Consumer { court ->
            Global.court = court
            tvCourt.text = court.dmms
        })
    }

}
