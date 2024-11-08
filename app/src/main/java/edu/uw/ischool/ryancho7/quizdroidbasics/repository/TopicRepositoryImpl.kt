//package edu.uw.ischool.ryancho7.quizdroidbasics.repository
//
//import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question
//import edu.uw.ischool.ryancho7.quizdroidbasics.models.Topic
//import java.io.BufferedReader
//import java.io.InputStreamReader
//
//class TopicRepositoryImpl(private val context: Context) : TopicRepository {
//
//    private val gson = Gson()
//    private val topics: List<Topic> by lazy { loadTopicsFromJson() }
//
//    override fun getAllTopics(): List<Topic> = topics
//
//    override fun getQuestionsForTopic(topicName: String): List<Question> {
//        return topics.find { it.title == topicName }?.questions.orEmpty()
//    }
//
//    private fun loadTopicsFromJson(): List<Topic> {
//        val assetManager = context.assets
//        assetManager.open("questions.json").use { inputStream ->
//            val reader = BufferedReader(InputStreamReader(inputStream))
//            val type = object : TypeToken<List<Topic>>() {}.type
//            return gson.fromJson(reader, type)
//        }
//    }
//}
package edu.uw.ischool.ryancho7.quizdroidbasics.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Topic
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.IOException

class TopicRepositoryImpl(private val context: Context) : TopicRepository {

    private val gson = Gson()
    private val jsonFilePath = File(context.filesDir, "data/questions.json")

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

    private fun copyJsonFromAssets() {
        try {
            context.assets.open("questions.json").use { inputStream ->
                // Ensure parent directory exists
                jsonFilePath.parentFile?.mkdirs()
                FileOutputStream(jsonFilePath).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
