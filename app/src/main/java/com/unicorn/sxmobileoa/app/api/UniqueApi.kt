package com.unicorn.sxmobileoa.app.api

import com.unicorn.sxmobileoa.app.network.model.Response
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UniqueApi {

    @POST("basePost")
//    @POST("request.shtml")
    fun post(@Query("fybm") fybm: String, @Body requestBody: RequestBody): Single<Response>

}