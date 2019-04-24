package com.unicorn.sxmobileoa.header.sbly.detail

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.title_recycler.*

class SblyDetailAct : BaseAct() {

    override val layoutId = R.layout.title_recycler

    val mAdapter = SblyDetailAdapter()

    override fun initViews() {
        titleBar.setTitle("设备领用详情")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SblyDetailAct)
            mAdapter.bindToRecyclerView(this)
            HorizontalDividerItemDecoration.Builder(context)
                    .colorResId(R.color.md_grey_100)
                    .size(ConvertUtils.dp2px(10f))
                    .build().let { this.addItemDecoration(it) }
        }
    }

    override fun bindIntent() {
        IntRange(1, 5)
                .map { Sbly(spd = Global.spd, position = it) }
                .let { mAdapter.setNewData(it) }
    }

}
