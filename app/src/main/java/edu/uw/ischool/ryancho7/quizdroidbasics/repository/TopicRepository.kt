package edu.uw.ischool.ryancho7.quizdroidbasics.repository

import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Topic

interface TopicRepository {
    fun getAllTopics(): List<Topic>
    fun getQuestionsForTopic(topicName: String): List<Question>
}
