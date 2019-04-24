package com.unicorn.sxmobileoa.select.code.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.code.model.CodeActNavigationModel
import com.unicorn.sxmobileoa.select.code.network.GetCode
import dart.DartModel
import kotlinx.android.synthetic.main.title_recycler.*

class CodeAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle(model.title)
        initRecyclerView()
    }

    private lateinit var mAdapter: CodeAdapter

    private fun initRecyclerView() {
        mAdapter = CodeAdapter(model.key)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        GetCode(model.code).toMaybe(this)
                .subscribe { mAdapter.setNewData(it) }
    }

    override val layoutId = R.layout.title_recycler

    @DartModel
    lateinit var model: CodeActNavigationModel

}
