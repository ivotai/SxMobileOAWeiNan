package com.unicorn.sxmobileoa.n.process.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.n.process.network.GetSqlcls
import kotlinx.android.synthetic.main.title_recycler.*

class ProcessAct : BaseAct() {

    override val layoutId: Int = R.layout.title_recycler

    override fun initViews() {
        titleBar.setTitle("流程")
        initRecyclerView()
    }

    private val mAdapter = ProcessAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    @SuppressLint("CheckResult")
    override fun bindIntent() {
        val processInstanceId = intent.getStringExtra(Key.processInstanceId)
        GetSqlcls(processInstanceId).toMaybe(this).subscribe {t->
            mAdapter.setNewData(t)
        }
    }

}
