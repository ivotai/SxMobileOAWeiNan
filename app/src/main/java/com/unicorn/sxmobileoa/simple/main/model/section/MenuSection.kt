package com.unicorn.sxmobileoa.simple.main.model.section

import com.chad.library.adapter.base.entity.SectionEntity
import com.unicorn.sxmobileoa.simple.main.model.Menu

class MenuSection(t: Menu? = null, header: String = "", isHeader: Boolean = false) : SectionEntity<Menu>(t) {

    init {
        this.header = header
        this.isHeader = isHeader
    }

}