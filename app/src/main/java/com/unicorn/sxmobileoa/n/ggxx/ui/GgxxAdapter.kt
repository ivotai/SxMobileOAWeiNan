package com.unicorn.sxmobileoa.n.ggxx.ui

import android.content.Intent
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.ggxx.model.Ggxx

class GgxxAdapter : BaseQuickAdapter<Ggxx, BaseViewHolder>(R.layout.item_text) {

    override fun convert(helper: BaseViewHolder, item: Ggxx) {
        helper.apply {
            val tvText = getView<TextView>(R.id.tvText)
            tvText.text = item.title
            tvText.safeClicks().subscribe { _ ->
                Intent(mContext, GgxxDetailAct::class.java).apply {
                    putExtra(Key.id, item.id)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}