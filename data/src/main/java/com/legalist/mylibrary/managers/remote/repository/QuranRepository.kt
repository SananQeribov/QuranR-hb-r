package com.legalist.mylibrary.managers.repository

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.ders.domain.util.ProgressBarCallback
import com.legalist.mylibrary.managers.api.RetrofitInstance
import com.legalist.mylibrary.managers.db.QuranDataStore
import com.legalist.mylibrary.managers.mapper.QuranMapper
import kotlinx.coroutines.delay
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class QuranRepository(private val context: Context, private val progressBarCallback: ProgressBarCallback) {
    private val dataStore = QuranDataStore(context)
    private val quranMapper = QuranMapper()

    suspend fun fetchQuranData(): JSONArray {
        return try {
            progressBarCallback.showProgressBar()
            val storedData = dataStore.getQuranData()
            val data = if (storedData != null) {
                Log.d("sssss","${storedData}")
                storedData

            } else {
                Log.d("nnnnn","${fetchDataFromRemote()}")

                fetchDataFromRemote()

            }

            progressBarCallback.hideProgressBar()
            data
        } catch (e: NoInternetException) {
            progressBarCallback.showProgressBar()
            println("xeta bas verdi")
            showNoInternetDialog()
            JSONArray()
        } catch (e: Exception) {
            progressBarCallback.hideProgressBar()
            println("xeta oldu")
            JSONArray()
        }
    }

    private suspend fun fetchDataFromRemote(): JSONArray {
        if (!isNetworkAvailable()) {
            throw NoInternetException("No internet connection")
        }

        return try {
            val arabicResponse = RetrofitInstance.api.getQuranInArabic()
           // val englishResponse = RetrofitInstance.api.getQuranInEnglish()



            val mergedSurahs = quranMapper.convertToJSONArray(arabicResponse)


            dataStore.saveQuranData(mergedSurahs)
            mergedSurahs
        } catch (e: IOException) {
            e.printStackTrace()
            JSONArray()
        } catch (e: HttpException) {
            e.printStackTrace()
            JSONArray()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(context)
            .setTitle("No Internet Connection")
            .setMessage("Please check your internet connection and try again.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}

class NoInternetException(message: String) : IOException(message)