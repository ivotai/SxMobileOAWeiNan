package com.unicorn.sxmobileoa.simple.main.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.csx.ui.SpcsxAct
import com.unicorn.sxmobileoa.simple.dbxx.ui.DbxxAct
import com.unicorn.sxmobileoa.simple.main.model.Menu
import kotlinx.android.synthetic.main.item_menu.*

@SuppressLint("CheckResult")
class MenuAdapter : BaseQuickAdapter<Menu, MyHolder>(R.layout.item_menu) {

    override fun convert(helper: MyHolder, item: Menu) {
        helper.apply {
            tvText.text = item.text
            tvCount.text = item.count.toString()
            flCount.visibility = if (item.count == 0) View.INVISIBLE else View.VISIBLE
            helper.setImageResource(R.id.ivImage, item.resId)
            root.safeClicks().subscribe {
                if (item.text == "超审限") {
                    mContext.startActivity(Intent(mContext, SpcsxAct::class.java))
                    return@subscribe
                }
                mContext.startActivity(Intent(mContext, DbxxAct::class.java).apply {
                    putExtra(Key.menu, item)
                })
            }
        }
    }

}