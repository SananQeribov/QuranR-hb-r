package com.legalist.quranrhbr.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ders.domain.model.Data
import com.ders.domain.model.ZikirResponse
import com.legalist.mylibrary.managers.api.ZikirApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ZikirViewModel : ViewModel() {
    private val zikirapi = ZikirApiClient()
    private val disposable = CompositeDisposable()

    val zikirs = MutableLiveData<ZikirResponse>()
    val zikirerror = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    fun refereshdata() {
        getdatafromApi()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getdatafromApi() {
        loading.value = true
        disposable.add(
            ZikirApiClient().getdata()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ZikirResponse>() {

                    override fun onSuccess(t: ZikirResponse) {

                        zikirs.value = t
                        zikirerror.value = false
                        loading.value = false
                        Log.e("AAAAAAAAAAAA",t.toString())
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        zikirerror.value = true
                        e.printStackTrace()
                    }

                })

        )

    }
}