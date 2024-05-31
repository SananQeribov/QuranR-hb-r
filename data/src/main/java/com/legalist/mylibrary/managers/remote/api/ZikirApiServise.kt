package com.legalist.mylibrary.managers.remote.api

import com.ders.domain.model.ZikirResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ZikirApiServise {
    //https://ayazprogrammer.github.io/host_api/name.json
    //Base-Url https://ayazprogrammer.github.io/
    //End point host_api/name.json
    @GET("host_api/name.json")
    fun getzikir(): Single<ZikirResponse>


}