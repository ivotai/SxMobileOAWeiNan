package com.unicorn.sxmobileoa.simple.main.ui

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.mess.RxBus
import com.unicorn.sxmobileoa.app.ui.BaseFra
import com.unicorn.sxmobileoa.commitTask.model.CommitTaskSuccess
import com.unicorn.sxmobileoa.simple.main.network.GetMenu
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fra_home.*


@SuppressLint("CheckResult")
class HomeFra : BaseFra() {

    /*
            nav_view.setNavigationItemSelectedListener { _ ->
            LoginOut().toMaybe(this).subscribe {
                drawer_layout.closeDrawers()
                startActivity(Intent(this@HomeFra, LoginAct::class.java))
                finish()
            }
            return@setNavigationItemSelectedListener true
        }
     */

    override val layoutId = R.layout.title_swipe_recycler

    override fun initViews() {
        titleBar.setTitle("${Global.court!!.dmms}移动办公", true)
        initRecyclerView()
    }

    private val menuAdapter = MenuAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 4)
            menuAdapter.bindToRecyclerView(this)
            addItemDecoration(GridDividerItemDecoration(1, ContextCompat.getColor(context, R.color.md_grey_300)))
        }
        menuAdapter.addHeaderView(HomeHeaderView(context!!))
        swipeRefreshLayout.setColorScheme(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener { getMenu() }
    }

    // 硬编码
    private val resIds = listOf(
            R.mipmap.swgl, R.mipmap.nbfw, R.mipmap.wbfw, R.mipmap.jdsq,
            R.mipmap.gcsq, R.mipmap.qjsq, R.mipmap.ycsq, R.mipmap.wply,
            R.mipmap.csx, R.mipmap.sbly, R.mipmap.sbwx, R.mipmap.sbbf
    )

    override fun bindIntent() {
        getMenu()
    }

    private fun getMenu() {
        GetMenu().toMaybe(this)
                .map { list ->
                    val temp = list.filter { it.moduleCode.isNotEmpty() }
                    temp.forEachIndexed { index, menu -> menu.resId = resIds[index] }
                    return@map temp
                }
                .subscribe {
                    swipeRefreshLayout.isRefreshing = false
                    menuAdapter.setNewData(it)
                }
    }

    override fun registerEvent() {
        RxBus.get().registerEvent(CommitTaskSuccess::class.java, this, Consumer {
            bindIntent()
        })
    }

}
