package com.legalist.quranrhbr.manager
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class AudioManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentAudioUrl: String? = null
    private var isPaused = false
    private var pausedPosition = 0

    init {
        initializeMediaPlayer()
    }

    private fun initializeMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener {
                start()
                if (isPaused) {
                    seekTo(pausedPosition)
                    isPaused = false
                }
            }
            setOnCompletionListener {
                stop()
                reset()
            }
            setOnErrorListener { _, _, _ ->
                reset()
                true
            }
        }
    }

    fun playAudio(filePath: String) {
        mediaPlayer?.let {
            if (currentAudioUrl == filePath && it.isPlaying) {
                it.pause()
                isPaused = true
                pausedPosition = it.currentPosition
            } else {
                it.reset()
                currentAudioUrl = filePath
                try {
                    it.setDataSource(filePath)
                    it.prepareAsync()
                } catch (e: IOException) {
                    Log.e("AudioManager", "Error setting data source", e)
                    Toast.makeText(context, "Error playing audio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun pauseAudio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                isPaused = true
                pausedPosition = it.currentPosition
            }
        }
    }

    fun stopAudio() {
        mediaPlayer?.let {
            if (it.isPlaying || isPaused) {
                it.stop()
                it.reset()
                isPaused = false
                pausedPosition = 0
            }
        }
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun downloadAudio(url: String, callback: (filePath: String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val urlConnection = URL(url).openConnection() as HttpURLConnection
                urlConnection.connect()
                val inputStream: InputStream = urlConnection.inputStream
                val file = File(context.filesDir, url.substringAfterLast("/"))
                val outputStream: OutputStream = FileOutputStream(file)
                val buffer = ByteArray(1024)
                var len: Int
                while (inputStream.read(buffer).also { len = it } != -1) {
                    outputStream.write(buffer, 0, len)
                }
                outputStream.close()
                inputStream.close()
                withContext(Dispatchers.Main) {
                    callback(file.absolutePath)
                }
            } catch (e: IOException) {
                Log.e("AudioManager", "Error downloading audio", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error downloading audio", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

