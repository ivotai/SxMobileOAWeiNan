package com.unicorn.sxmobileoa.select.deptUser.ui

import android.arch.lifecycle.LifecycleOwner
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.select.dept.model.Dept
import com.unicorn.sxmobileoa.select.deptUser.model.User
import com.unicorn.sxmobileoa.select.deptUser.network.DeptUser
import io.reactivex.Observable

class DeptUserAdapter : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(null) {

    var single = false

    companion object {
        const val TYPE_DEPT = 0
        const val TYPE_USER = 1
    }

    init {
        addItemType(TYPE_DEPT, R.layout.item_text)
        addItemType(TYPE_USER, R.layout.item_text)
    }

    override fun convert(helper: BaseViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            TYPE_DEPT -> {
                item as Dept
                val tvText = helper.getView<TextView>(R.id.tvText)
                tvText.text = item.text
                // 点击后请求
                tvText.safeClicks().subscribe {
                    if (item.userList == null) {
                        getUser(item, helper.adapterPosition)
                        return@subscribe
                    }
                    if (item.isExpanded) collapse(helper.adapterPosition)
                    else expand(helper.adapterPosition)
                }
            }
            TYPE_USER -> {
                item as User
                val tvText = helper.getView<TextView>(R.id.tvText)
                tvText.text = item.fullname

                val dp16 = ConvertUtils.dp2px(16f)
                val dp32 = dp16 * 2
                tvText.setPadding(dp32, dp16, dp16, dp16)

                // 选中效果
                tvText.setTextColor(if (item.isSelected) Color.WHITE else Color.BLACK)
                tvText.setBackgroundColor(if (item.isSelected) ContextCompat.getColor(mContext, R.color.colorPrimary) else Color.WHITE)

                // 点击后刷新对应条目
                tvText.safeClicks().subscribe { _ ->
                    // 单选
                    if (single){
                        Observable.fromIterable(data)
                                .ofType(Dept::class.java)
                                .filter { it.userList != null }
                                .flatMap { Observable.fromIterable(it.userList) }
                                .subscribe {  it.isSelected = it == item }
                        notifyDataSetChanged()
                   return@subscribe
                    }


                    // 多选
                    item.isSelected = !item.isSelected
                    notifyItemChanged(helper.adapterPosition)
                }
            }
        }
    }

    private fun getUser(dept: Dept, position: Int) {
        DeptUser(dept.id).toMaybe(mContext as LifecycleOwner).subscribe { userList ->
            if (userList.isEmpty()) return@subscribe
            dept.userList = userList
            expand(position)
        }
    }

}