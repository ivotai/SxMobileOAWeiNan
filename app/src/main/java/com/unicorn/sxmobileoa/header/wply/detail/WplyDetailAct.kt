package com.unicorn.sxmobileoa.header.wply.detail

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.get
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.wply.model.Wply
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.title_recycler.*

class WplyDetailAct : BaseAct() {

    override val layoutId = R.layout.title_recycler

    lateinit var mAdapter: WplyAdapter


    override fun initViews() {
        titleBar.setTitle("物品领用详情")
        mAdapter = WplyAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@WplyDetailAct) as LinearLayoutManager
            mAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_100)
                    .size(ConvertUtils.dp2px(10f))
                    .build().let { this.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
        val spd = Global.spd
        var wpgs_input = spd.get(Key.wpgs_input)
        if (wpgs_input.isEmpty()) wpgs_input = "5"
        val wpgs = wpgs_input.toInt()
        IntRange(1, wpgs)
                .map { Wply(spd = spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
