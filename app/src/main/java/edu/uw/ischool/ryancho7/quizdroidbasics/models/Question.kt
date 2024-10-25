package edu.uw.ischool.ryancho7.quizdroidbasics.models

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswer: Int
)