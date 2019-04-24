package com.unicorn.sxmobileoa.app.network.model

// Response 的简化形式，便于下游处理
data class SimpleResponse<Model>(
        val code: String,
        val msg: String,
        var message: Int? = null,
        var reMess:String = "",
        var result: Model? = null
)
