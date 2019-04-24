package com.unicorn.sxmobileoa.simple.dbxx.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra
import com.unicorn.sxmobileoa.app.ui.page.model.Page
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.simple.dbxx.model.Dbxx
import com.unicorn.sxmobileoa.simple.dbxx.network.GetDbxx
import com.unicorn.sxmobileoa.simple.main.model.Menu
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import io.reactivex.Maybe
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_swipe_recycler.*

class DbxxFra : BaseFra(), PageActOrFra<Dbxx> {

    override var mAdapter: BaseQuickAdapter<Dbxx, BaseViewHolder>? = null

    override fun initViews() {
        mAdapter = DbxxAdapter(menu, type)
        super.initViews()
        HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.md_grey_100)
                .size(ConvertUtils.dp2px(10f))
                .build().let { mRecyclerView.addItemDecoration(it) }
    }

    override fun loadPage(pageNo: Int): Maybe<Page<Dbxx>> = GetDbxx(pageNo, menu, type).toMaybe(this).doAfterSuccess {
        val index = if (type.toInt() == 1) 0 else 1
        RxBus.get().post(IndexCount(index, it.total))
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(CommitTaskSuccess::class.java, this, Consumer {
            loadFirstPage()
        })
    }

    lateinit var menu: Menu

    lateinit var type: String

    override fun initArguments() {
        arguments!!.apply {
            type = getString(Key.type)
            menu = getSerializable(Key.menu) as Menu
        }
    }

    override val layoutId = R.layout.act_swipe_recycler

    override val mSwipeRefreshLayout: SwipeRefreshLayout
        get() = swipeRefreshLayout

    override val mRecyclerView: RecyclerView
        get() = recyclerView

}