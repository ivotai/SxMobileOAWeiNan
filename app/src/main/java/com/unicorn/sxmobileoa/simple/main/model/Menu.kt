package com.unicorn.sxmobileoa.simple.main.model

import java.io.Serializable

data class Menu(
        val moduleCode: String,
        val flowCode: String,
        val spdCode: String,
        val text: String,
        val count: Int,
        val ybCount: Int,
        var resId: Int,
        val isGd: Int
) : Serializable

