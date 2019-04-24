package com.unicorn.sxmobileoa.header.ycsq.cllx.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.header.ycsq.cllx.model.CllxActNavigationModel
import com.unicorn.sxmobileoa.header.ycsq.cllx.network.GetCllx
import dart.DartModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.title_recycler.*

class CllxAct : BaseAct() {

    override fun initViews() {
        val title = intent.getStringExtra(Key.title)
        titleBar.setTitle(title)
        initRecyclerView()
    }

    private lateinit var mAdapter: CllxAdapter

    private fun initRecyclerView() {
        mAdapter = CllxAdapter(model.key)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        GetCllx().toMaybe(this).toObservable()
                .flatMap { Observable.fromIterable(it) }
                .flatMap { Observable.fromIterable(it.children) }
                .toList()
                .subscribe { t -> mAdapter.setNewData(t) }
    }

    override val layoutId = R.layout.title_recycler

    @DartModel
    lateinit var model: CllxActNavigationModel

}
