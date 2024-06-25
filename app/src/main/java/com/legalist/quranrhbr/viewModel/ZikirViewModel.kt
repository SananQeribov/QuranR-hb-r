package com.legalist.quranrhbr.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
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



    // Connectivity manager to check for internet connection
    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun refereshdata() {
        getDataFromRoom() //
    }

    private fun isInternetAvailable(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getdatafromApi() {
        loading.value = true
        disposable.add(
            zikirapi.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ZikirResponse>() {

                    override fun onSuccess(t: ZikirResponse) {
                        Log.d("ZikirViewModel", "Data fetched from API successfully")
                        saveroom(t.data.map {
                            Zikr(it.arabicName, it.englishTranslation, it.id, it.transliteration)
                        })
                    }

                    override fun onError(e: Throwable) {
                        Log.e("ZikirViewModel", "Error fetching data from API", e)
                        loading.value = false
                        zikirerror.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showAllahnames(list: List<Zikr>): List<Zikr> {
        zikirs.value = list
        zikirerror.value = false
        loading.value = false
        return list
    }

    private fun saveroom(list: List<Zikr>) {
        launch(Dispatchers.IO) {
            ZikrDatabase.getDao().insertAll(list)
            Log.d("ZikirViewModel", "Data saved to local database")
            withContext(Dispatchers.Main) {
                getDataFromRoom()
            }
        }
    }private fun getDataFromRoom() {
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val data = ZikrDatabase.getDao().getdataAll()
            data.collectLatest {
                withContext(Dispatchers.Main) {
                    if (it.isNotEmpty()) {
                        showAllahnames(it)
                        Log.d(
                            "ZikirViewModel",
                            "Data fetched from local database: ${it.size} items"
                        )
                    } else {
                        Log.d("ZikirViewModel", "Local database is empty, trying to fetch from API")
                        if (isInternetAvailable()) {
                            getdatafromApi()
                        } else {
                            Log.d("ZikirViewModel", "No internet and local database is empty")
                            zikirerror.value = true
                            loading.value = false
                        }
                    }
                }
            }
        }
    }override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
