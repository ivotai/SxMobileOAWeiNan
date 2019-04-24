package com.unicorn.sxmobileoa.simple.court.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.simple.court.network.GetCourt
import kotlinx.android.synthetic.main.title_recycler.*

class CourtAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择法院")
        initRecyclerView()
    }

    private val mAdapter = CourtAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    private lateinit var headerView: KeywordHeaderView

    private fun addHeaderView() {
        headerView = KeywordHeaderView(this).apply {
            setHint("请输入法院全称")
            mAdapter.addHeaderView(this)
        }
    }

    override fun bindIntent() {
        GetCourt().toMaybe(this)
                .subscribe {
                    mAdapter.setNewData(it)
                    addHeaderView()
                    textChangeKeyword(it)
                }
    }

    private fun textChangeKeyword(allCourt: List<Court>) {
        headerView.etKeyword.textChanges2()
                .subscribe { keyword ->
                    allCourt.filter { it.dmms.contains(keyword) }
                            .let { mAdapter.setNewData(it) }
                }
    }

    override val layoutId = R.layout.title_recycler

}
