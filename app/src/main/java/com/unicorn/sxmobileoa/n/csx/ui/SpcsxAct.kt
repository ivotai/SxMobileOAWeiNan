package com.unicorn.sxmobileoa.n.csx.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.csx.network.GetSpcsx
import kotlinx.android.synthetic.main.title_recycler.*

class SpcsxAct : BaseAct() {

    override val layoutId = R.layout.title_recycler

    override fun initViews() {
        titleBar.setTitle("超审限")
        initRecyclerView()
    }

    private val mAdapter = CsxAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SpcsxAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
            mAdapter.setEmptyView(R.layout.empty_view,this)
        }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        GetSpcsx().toMaybe(this).subscribe {
            mAdapter.setNewData(it)
        }
    }

}
