package com.unicorn.sxmobileoa.n.ggxx.ui

import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.simple.dbxx.ui.IndexCount
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.title_tab_viewpager.*

class GgxxPagerFra : BaseFra() {

    override val layoutId = R.layout.title_tab_viewpager

    override fun initViews() {
        titleBar.setTitle("消息公告",true)
        viewPager.adapter = GgxxPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun bindIntent() {
    }

    override fun registerEvent() {
        RxBus.get().registerEventOnMain(IndexCount::class.java, this, Consumer {
            val text = if (it.index == 0) "未读(" else "已读("
            val tab = tabLayout.getTabAt(it.index)
            tab!!.text = "$text${it.count})"
        })
    }
}
