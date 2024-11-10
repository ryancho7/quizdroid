package edu.uw.ischool.ryancho7.quizdroidbasics.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Topic
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class TopicRepositoryImpl(private val context: Context) : TopicRepository {
    private val gson = Gson()
    private val jsonFilePath = File(context.filesDir, "data/questions.json")
    private val executor = Executors.newSingleThreadExecutor()
    private var onDownloadCompleteListener: OnDownloadCompleteListener? = null

    interface OnDownloadCompleteListener {
        fun onDownloadComplete(success: Boolean, message: String)
    }

    fun setOnDownloadCompleteListener(listener: OnDownloadCompleteListener) {
        onDownloadCompleteListener = listener
    }

    init {
        if (!jsonFilePath.exists()) {
            copyJsonFromAssets()
        }
    }

    override fun getAllTopics(): List<Topic> {
        return if (jsonFilePath.exists()) {
            try {
                val fileReader = FileReader(jsonFilePath)
                gson.fromJson(fileReader, object : TypeToken<List<Topic>>() {}.type)
            } catch (e: IOException) {
                e.printStackTrace()
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    override fun getQuestionsForTopic(topicName: String): List<Question> {
        return getAllTopics().find { it.title == topicName }?.questions ?: emptyList()
    }

    fun downloadJsonInBackground() {
        val url = getPreferencesUrl(context)
        Toast.makeText(context, "Downloading from $url", Toast.LENGTH_SHORT).show()

        executor.execute {
            try {
                val urlConnection = URL(url).openConnection() as HttpURLConnection
                val responseStream = ByteArrayOutputStream()
                try {
                    val incoming = BufferedInputStream(urlConnection.inputStream).bufferedReader()
                    incoming.forEachLine {
                        responseStream.write(it.toByteArray())
                    }
                } finally {
                    urlConnection.disconnect()
                }

                val data = responseStream.toString()
                if (data.isNotEmpty()) {
                    saveDataToFile(data)
                    onDownloadCompleteListener?.onDownloadComplete(true, "Download successful")
                } else {
                    onDownloadCompleteListener?.onDownloadComplete(false, "Failed: Empty response")
                }
            } catch (e: IOException) {
                Log.e("TopicRepositoryImpl", "Download failed", e)
                onDownloadCompleteListener?.onDownloadComplete(false, "Download failed: ${e.message}")
            }
        }
    }

    private fun saveDataToFile(data: String) {
        try {
            jsonFilePath.parentFile?.mkdirs()
            FileOutputStream(jsonFilePath).use { outputStream ->
                outputStream.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun copyJsonFromAssets() {
        try {
            context.assets.open("questions.json").use { inputStream ->
                jsonFilePath.parentFile?.mkdirs()
                FileOutputStream(jsonFilePath).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getPreferencesUrl(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("quiz_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("data_url", "http://tednewardsandbox.site44.com/questions.json") ?: ""
    }
}
