package com.unicorn.sxmobileoa.n.ggxx.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Key

class GgxxPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val isRead = position.toString()
        val args = Bundle()
        args.putString(Key.isRead, isRead)
        val fra = GgxxFra()
        fra.arguments = args
        return fra
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "未读" else "已读"
    }

}