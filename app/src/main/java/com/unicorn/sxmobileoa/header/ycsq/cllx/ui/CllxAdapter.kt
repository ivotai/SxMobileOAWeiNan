package com.unicorn.sxmobileoa.header.ycsq.cllx.ui

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.finish
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.header.ycsq.cllx.model.Cllx

class CllxAdapter(val key: String) : BaseQuickAdapter<Cllx, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Cllx) {
        helper.apply {
            val tvText = getView<TextView>(R.id.tvText)
            tvText.text = item.name

            // 单选
            tvText.safeClicks().subscribe {
                RxBus.get().post(TextResult(key, item.name))
                mContext.finish()
            }
        }
    }

}