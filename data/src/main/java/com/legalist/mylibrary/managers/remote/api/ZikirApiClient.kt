package com.legalist.mylibrary.managers.remote.api

import com.ders.domain.model.ZikirResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ZikirApiClient {
    //Base-Url https://ayazprogrammer.github.io/
    //End point host_api/name.json
    private val Base_Url = "https://ayazprogrammer.github.io/"
    private val api = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build())
        .build()
        .create(ZikirApiServise::class.java)

    fun getdata(): Single<ZikirResponse> {
        return api.getzikir()

    }

}