package com.unicorn.sxmobileoa.simple.main.ui

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.n.MailFra
import com.unicorn.sxmobileoa.n.MyFra
import com.unicorn.sxmobileoa.n.ggxx.ui.GgxxPagerFra
import com.unicorn.sxmobileoa.n.news.ui.NewsFra

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = when (position) {
        0 -> HomeFra()
        1 -> GgxxPagerFra()
        2 -> NewsFra()
        3 -> MailFra()
        else -> MyFra()
    }

    override fun getCount() = 5

    private val pageTitles = listOf("首页", "公告", "新闻", "邮箱", "我的")

    override fun getPageTitle(position: Int) = pageTitles[position]

}