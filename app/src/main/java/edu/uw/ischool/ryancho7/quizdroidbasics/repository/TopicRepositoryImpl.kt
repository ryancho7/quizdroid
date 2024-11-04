package edu.uw.ischool.ryancho7.quizdroidbasics.repository

import android.content.Context
import edu.uw.ischool.ryancho7.quizdroidbasics.R
import edu.uw.ischool.ryancho7.quizdroidbasics.data.QuizData
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Topic
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question

class TopicRepositoryImpl(private val context: Context) : TopicRepository {

    private val topics: List<Topic> = listOf(
        Topic(
            title = context.getString(R.string.topic_math_title),
            shortDescription = context.getString(R.string.topic_math_description),
            longDescription = context.getString(R.string.topic_math_long_description),
            questions = QuizData.getMathQuestions()
        ),
        Topic(
            title = context.getString(R.string.topic_physics_title),
            shortDescription = context.getString(R.string.topic_physics_description),
            longDescription = context.getString(R.string.topic_physics_long_description),
            questions = QuizData.getPhysicsQuestions()
        ),
        Topic(
            title = context.getString(R.string.topic_marvel_title),
            shortDescription = context.getString(R.string.topic_marvel_description),
            longDescription = context.getString(R.string.topic_marvel_long_description),
            questions = QuizData.getMarvelQuestions()
        ),
        Topic(
            title = context.getString(R.string.topic_ghibli_title),
            shortDescription = context.getString(R.string.topic_ghibli_description),
            longDescription = context.getString(R.string.topic_ghibli_long_description),
            questions = QuizData.getStudioGhibliQuestions()
        ),
        Topic(
            title = context.getString(R.string.topic_mario_title),
            shortDescription = context.getString(R.string.topic_mario_description),
            longDescription = context.getString(R.string.topic_mario_long_description),
            questions = QuizData.getMarioQuestions()
        ),
        Topic(
            title = context.getString(R.string.topic_hp_title),
            shortDescription = context.getString(R.string.topic_hp_description),
            longDescription = context.getString(R.string.topic_hp_long_description),
            questions = QuizData.getHarryPotterQuestions()
        )
    )

    override fun getAllTopics(): List<Topic> = topics

    override fun getQuestionsForTopic(topicName: String): List<Question> {
        return topics.find { it.title == topicName }?.questions.orEmpty()
    }
}
