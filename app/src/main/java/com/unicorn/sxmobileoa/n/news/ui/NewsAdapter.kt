package com.unicorn.sxmobileoa.n.news.ui

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.unicorn.sxmobileoa.R
import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.mess.MyHolder
import com.unicorn.sxmobileoa.app.safeClicks
import com.unicorn.sxmobileoa.n.news.model.News
import kotlinx.android.synthetic.main.item_text.*

class NewsAdapter : BaseQuickAdapter<News, MyHolder>(R.layout.item_text) {

    override fun convert(helper: MyHolder, item: News) {
        helper.apply {
            tvText.text = item.title
            tvText.safeClicks().subscribe { _ ->
                Intent(mContext, NewsDetailAct::class.java).apply {
                    putExtra(Key.contentId, item.content_id)
                }.let { mContext.startActivity(it) }
            }
        }
    }

}