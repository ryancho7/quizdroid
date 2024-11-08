package edu.uw.ischool.ryancho7.quizdroidbasics.models

data class Question(
    val text: String,
    val answers: List<String>,
    val answer: Int
)