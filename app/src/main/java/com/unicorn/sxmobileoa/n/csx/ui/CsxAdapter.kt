package com.unicorn.sxmobileoa.n.csx.ui

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.n.csx.model.Csx
import kotlinx.android.synthetic.main.item_csx.*

class CsxAdapter : BaseQuickAdapter<Csx, MyHolder>(R.layout.item_csx) {

    @SuppressLint("SetTextI18n")
    override fun convert(helper: MyHolder, item: Csx) {
        helper.apply {
            tvAhqc.text = "${adapterPosition+1}. ${item.ahqc}"
            tvCsxts.text = "(超期${item.csxts}天)"
        }
    }

}