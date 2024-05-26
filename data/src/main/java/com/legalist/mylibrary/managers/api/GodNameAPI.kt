package com.legalist.mylibrary.managers.api


import com.ders.domain.model.GodNameResponseData

import io.reactivex.Observable
import retrofit2.http.GET


//https://ayazprogrammer.github.io/host_api/name.json
interface GodNameAPI {
    @GET("name.json")
    fun getData(): Observable<GodNameResponseData>
}
