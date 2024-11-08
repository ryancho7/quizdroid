package edu.uw.ischool.ryancho7.quizdroidbasics.models

data class Topic(
    val title: String,
    val desc: String,
    val questions: List<Question>
)