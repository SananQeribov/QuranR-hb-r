package com.legalist.quranrhbr.utils

import android.content.Context
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {
    fun writeJsonToFile(context: Context, jsonArray: JSONArray, fileName: String) {
        val file = File(context.filesDir, fileName)
        var fileOutputStream: FileOutputStream? = null

        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(jsonArray.toString().toByteArray())
            fileOutputStream.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }
    }
}
