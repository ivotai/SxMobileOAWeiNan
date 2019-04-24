package com.unicorn.sxmobileoa.simple.court.ui

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.finish
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.simple.court.model.Court

class CourtAdapter : BaseQuickAdapter<Court, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Court) {
        helper.apply{
            val tvText = getView<TextView>(R.id.tvText)
            tvText.text = item.dmms

            tvText.safeClicks().subscribe {
                RxBus.get().post(item)
                mContext.finish()
            }
        }
    }

}