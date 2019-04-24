package com.unicorn.sxmobileoa.select.deptUser.ui

import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.addDefaultItemDecoration
import com.unicorn.sxmobileoa.app.mess.KeywordHeaderView
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.mess.model.TextResult
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.app.textChanges2
import com.unicorn.sxmobileoa.app.ui.BaseAct
import com.unicorn.sxmobileoa.select.dept.model.Dept
import com.unicorn.sxmobileoa.select.dept.network.GetDept
import com.unicorn.sxmobileoa.select.deptUser.model.DeptUserActNavigationModel
import com.unicorn.sxmobileoa.select.deptUser.model.DeptUserResult
import dart.DartModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.title_recycler.*

class DeptUserAct : BaseAct() {

    override fun initViews() {
        titleBar.setTitle("选择人员")
        initRecyclerView()
    }

    private val mAdapter = DeptUserAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DeptUserAct)
            mAdapter.bindToRecyclerView(this)
            addDefaultItemDecoration()
        }
        mAdapter.single = model.single == true.toString()
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
        }.etKeyword.textChanges2().subscribe { keyword ->
            allDept.filter { it.text.contains(keyword) }
                    .let { mAdapter.setNewData(it) }
        }
    }

    private fun clickOperation() {
        titleBar.setOperation("确认").safeClicks().subscribe { _ ->
            Observable.fromIterable(mAdapter.data)
                    .ofType(Dept::class.java)
                    .filter { it.userList != null }
                    .flatMap { Observable.fromIterable(it.userList) }
                    .filter { it.isSelected }
                    .toList()
                    .subscribe { selectors ->
                        if (selectors.isEmpty()){
                            ToastUtils.showShort("请选择人员")
                            return@subscribe
                        }
                        when (model.type) {
                            Key.textResult -> {
                                val result = selectors.joinToString(",") { it.fullname }
                                RxBus.get().post(TextResult(key = model.key, result = result))
                            }
                            Key.deptUserResult -> {
                                selectors.forEach { it.id = "${it.courtCode}_${it.id}" }
                                RxBus.get().post(DeptUserResult(selectors))
                            }
                        }
                        finish()
                    }
        }
    }

    @DartModel
    lateinit var model: DeptUserActNavigationModel

    override val layoutId = R.layout.title_recycler

}