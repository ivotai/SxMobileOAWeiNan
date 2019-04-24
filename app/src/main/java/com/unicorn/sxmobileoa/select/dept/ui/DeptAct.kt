package com.unicorn.sxmobileoa.select.dept.ui

import android.support.v7.widget.LinearLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.dept.model.Dept
import com.unicorn.sxmobileoa.select.dept.model.DeptActNavigationModel
import com.unicorn.sxmobileoa.select.dept.network.GetDept
import dart.DartModel
import kotlinx.android.synthetic.main.title_recycler.*

class DeptAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择部门")
        initRecyclerView()
    }

    private val mAdapter = DeptAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
    }

    override fun bindIntent() {
        getDept()
        clickOperation()
    }

    private fun getDept() {
        GetDept().toMaybe(this)
                .map { it.deptData }
                .doOnSuccess { textChangeKeyword(it) }
                .subscribe { mAdapter.setNewData(it) }
    }

    private fun textChangeKeyword(allDept: List<Dept>) {
        KeywordHeaderView(this).apply {
            setHint("请输入部门")
            mAdapter.addHeaderView(this)
        }.etKeyword.textChanges2()
                .subscribe { keyword ->
                    allDept.filter { dept -> dept.text.contains(keyword) }
                            .let { mAdapter.setNewData(it) }
                }
    }

    private fun clickOperation() {
        titleBar.setOperation("确认").safeClicks().subscribe { _ ->
            mAdapter.data.filter { it.isSelected }
                    .let { selectors ->
                        val result = selectors.joinToString(",") { it.text }
                        RxBus.get().post(TextResult(key = model.key, result = result))
                    }
            finish()
        }
    }

    @DartModel
    lateinit var model: DeptActNavigationModel

    override val layoutId = R.layout.title_recycler

}
