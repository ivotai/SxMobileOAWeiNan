package com.unicorn.sxmobileoa.sequenceFlow.ui

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.select.deptUser.ui.DeptUserAct

class UserAdapter : BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_text) {

    var single = false

    override fun convert(helper: BaseViewHolder, item: User) {
        val tvText = helper.getView<TextView>(R.id.tvText)
        tvText.text = item.fullname

        // 选中效果
        tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
        tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

        // 点击后刷新对应条目
        tvText.safeClicks().subscribe {
            if (item.fullname == "选择其他人员") {
                mContext.startActivity(Intent(mContext, DeptUserAct::class.java).apply {
                    putExtra(Key.type, Key.deptUserResult)
                    putExtra(Key.single, single.toString())
                })
                return@subscribe
            }

            // 单选
            if (single) {
                data.forEach {
                    it.isSelected = it == item
                }
                notifyDataSetChanged()
                return@subscribe
            }

            // 多选
            item.isSelected = !item.isSelected
            notifyItemChanged(helper.adapterPosition)
        }
    }

}

