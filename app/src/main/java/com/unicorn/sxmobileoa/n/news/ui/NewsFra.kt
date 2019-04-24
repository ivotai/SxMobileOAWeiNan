package com.unicorn.sxmobileoa.n.news.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.n.news.network.GetNews
import kotlinx.android.synthetic.main.title_recycler.*

class NewsFra : BaseFra() {

    override val layoutId = R.layout.title_recycler

    override fun initViews() {
        titleBar.setTitle("新闻中心", true)
        initRecyclerView()
    }

    private val mAdapter = NewsAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
            mAdapter.setEmptyView(R.layout.empty_view,this)
        }
    }

    override fun bindIntent() {
        getNews()
    }

    @SuppressLint("CheckResult")
    private fun getNews() {
        GetNews().toMaybe(this).subscribe {
            mAdapter.setNewData(it)
        }
    }

}