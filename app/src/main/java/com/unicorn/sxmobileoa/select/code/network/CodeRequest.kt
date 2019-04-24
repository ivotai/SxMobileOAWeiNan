package com.unicorn.sxmobileoa.select.code.network

import com.unicorn.sxmobileoa.app.network.model.MaybeRequest

class CodeRequest(code: String) : MaybeRequest("seleData") {

    init {
        addParameter("dataCode", code)
    }

}