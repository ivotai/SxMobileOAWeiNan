package com.unicorn.sxmobileoa.n.csx.ui

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.n.ggxx.ui.GgxxPagerAdapter
import com.unicorn.sxmobileoa.simple.dbxx.ui.IndexCount
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.title_tab_viewpager.*

class CsxPagerAct : BaseAct() {

    override val layoutId = R.layout.title_tab_viewpager

    override fun initViews() {
        titleBar.setTitle("超审限")
        viewPager.adapter = CsxPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {
    }

    override fun registerEvent() {
        RxBus.get().registerEventOnMain(IndexCount::class.java, this, Consumer {
            val text = if (it.index == 0) "超审限(" else "即将超审限("
            val tab = tabLayout.getTabAt(it.index)
            tab!!.text = "$text${it.count})"
        })
    }
}
