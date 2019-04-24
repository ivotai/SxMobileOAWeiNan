package com.unicorn.sxmobileoa.simple.court.model

data class Court(
        val area: String,
        val czbz: String,
        val dm: String,     // 代码
        val dmms: String,   // 法院全称
        val fydz: String,
        val fyjb: Int,
        val fyjc: String,
        val gfdm: Int,
        val qybz: Int,
        val sjdm: String,   // 上级代码
        val sm: String,
        val xssx: String
)