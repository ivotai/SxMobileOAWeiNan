package com.unicorn.sxmobileoa.n.csx.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.n.csx.network.GetCsx
import com.unicorn.sxmobileoa.simple.dbxx.ui.IndexCount
import kotlinx.android.synthetic.main.recycler.*

class CsxFra : BaseFra() {

    override val layoutId = R.layout.recycler

    var type = 0

    override fun initArguments() {
        type = arguments!!.getInt(Key.type)
    }

    override fun initViews() {
        initRecyclerView()
    }

    private lateinit var mAdapter : CsxAdapter

    private fun initRecyclerView() {
        mAdapter = CsxAdapter(type ==1)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
            mAdapter.setEmptyView(R.layout.empty_view, this)
        }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        GetCsx(type).toMaybe(this).subscribe {
            mAdapter.setNewData(it)
            val index = if (type == 1) 0 else 1
            RxBus.get().post(IndexCount(index, it.size))
        }
    }

}
