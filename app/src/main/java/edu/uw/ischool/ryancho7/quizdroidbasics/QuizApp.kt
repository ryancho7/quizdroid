package edu.uw.ischool.ryancho7.quizdroidbasics

import android.app.Application
import android.util.Log
import edu.uw.ischool.ryancho7.quizdroidbasics.repository.TopicRepository
import edu.uw.ischool.ryancho7.quizdroidbasics.repository.TopicRepositoryImpl

class QuizApp : Application() {

    // Repository instance for accessing topics
    lateinit var topicRepository: TopicRepository

    override fun onCreate() {
        super.onCreate()
        // Initialize repository
        topicRepository = TopicRepositoryImpl(applicationContext)
        Log.d("QuizApp", "QuizApp initialized and repository created")
    }
}
