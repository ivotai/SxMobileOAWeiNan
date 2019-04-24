package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.simple.main.model.Menu

class DbxxPagerAdapter(fm: FragmentManager, val menu: Menu) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val args = Bundle().apply {
            putString(Key.type, if (position == 0) "1" else "2")
            putSerializable(Key.menu, menu)
        }
        val fra = DbxxFra()
        fra.arguments = args
        return fra
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "待办" else "已办"
    }

}