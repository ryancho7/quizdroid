package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import edu.uw.ischool.ryancho7.quizdroidbasics.R
import edu.uw.ischool.ryancho7.quizdroidbasics.data.QuizData

class TopicOverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the topic name from arguments
        val topic = arguments?.getString("topic") ?: getString(R.string.topic_title_default)

        // Retrieve the total number of questions for the topic
        val totalQuestions = when (topic) {
            "Math" -> QuizData.getMathQuestions().size
            "Physics" -> QuizData.getPhysicsQuestions().size
            "Marvel Super Heroes" -> QuizData.getMarvelQuestions().size
            "Studio Ghibli" -> QuizData.getStudioGhibliQuestions().size
            "Mario" -> QuizData.getMarioQuestions().size
            "Harry Potter" -> QuizData.getHarryPotterQuestions().size
            else -> 0
        }

        // Set the topic title, description, and total number of questions
        view.findViewById<TextView>(R.id.tvTopicTitle).text = topic
        view.findViewById<TextView>(R.id.tvDescription).text = getString(R.string.topic_description)
        view.findViewById<TextView>(R.id.tvTotalQuestions).text = getString(R.string.total_questions, totalQuestions)

        // Begin button to navigate to QuizQuestionFragment
        view.findViewById<Button>(R.id.btnBegin).setOnClickListener {
            val bundle = Bundle().apply {
                putString("topic", topic)
            }
            val fragment = QuizQuestionFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
