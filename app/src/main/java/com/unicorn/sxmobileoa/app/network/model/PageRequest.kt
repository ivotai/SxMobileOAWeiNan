package com.unicorn.sxmobileoa.app.network.model

import com.unicorn.sxmobileoa.app.Key
import com.unicorn.sxmobileoa.app.ui.page.PageActOrFra

open class PageRequest(busiCode: String, pageNo: Int) : MaybeRequest(busiCode = busiCode) {

    init {
        addParameter(Key.start, pageNo.toString())
        addParameter(Key.count, PageActOrFra.rows.toString())
    }

}