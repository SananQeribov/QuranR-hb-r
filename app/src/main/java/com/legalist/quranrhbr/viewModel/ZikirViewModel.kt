package com.legalist.quranrhbr.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ders.domain.model.ZikirResponse
import com.legalist.mylibrary.managers.local.entity.Zikr
import com.legalist.mylibrary.managers.local.room.db.ZikrDatabase
import com.legalist.mylibrary.managers.remote.api.ZikirApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZikirViewModel(application: Application) : BaseViewModel(application) {
    private val zikirapi = ZikirApiClient()
    private val disposable = CompositeDisposable()
    val zikirs = MutableLiveData<List<Zikr>>()
    val zikirerror = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refereshdata() {
        getdatafromApi()
        getDataFromRoom()
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
                        saveroom(t.data.map {
                            Zikr(it.arabicName, it.englishTranslation, it.id, it.transliteration)
                        })
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        zikirerror.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showAllahnames(list: List<Zikr>) {
        zikirs.value = list
        zikirerror.value = false
        loading.value = false
    }

    private fun saveroom(list: List<Zikr>) {
        launch(Dispatchers.IO) {
            ZikrDatabase.getDao().insertAll(list)
        }
    }

    private fun getDataFromRoom() {
        launch(Dispatchers.IO) {
            ZikrDatabase.getDao().getdataAll().collectLatest {
                withContext(Dispatchers.Main){
                    showAllahnames(it)
                    Log.d("SSSSSSSSSSSS", it.size.toString())
                }
            }
        }
    }
}