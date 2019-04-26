package com.unicorn.sxmobileoa.n.csx.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.unicorn.sxmobileoa.app.Key

class CsxPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val args = Bundle()
        args.putInt(Key.type, if (position == 0) 1 else 0)
        val fra = CsxFra()
        fra.arguments = args
        return fra
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "超审限" else "即将超审限"
    }

}