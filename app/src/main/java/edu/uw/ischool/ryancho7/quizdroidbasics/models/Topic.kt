package edu.uw.ischool.ryancho7.quizdroidbasics.models

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questions: List<Question>
)
