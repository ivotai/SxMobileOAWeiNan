package com.unicorn.sxmobileoa.n.ggxx.model

data class GgxxDetail(
    val content: String,
    val createDate: String,
    val fjlist: List<Fjlist>,
    val id: String,
    val sendUserName: String,
    val title: String
)

data class Fjlist(
    val fj: String,
    val fjname: String
)