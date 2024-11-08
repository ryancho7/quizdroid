package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import edu.uw.ischool.ryancho7.quizdroidbasics.QuizApp
import edu.uw.ischool.ryancho7.quizdroidbasics.R

class TopicOverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = (requireActivity().application as QuizApp).topicRepository
        val topicTitle = arguments?.getString("topic") ?: ""
        val topic = repository.getAllTopics().find { it.title == topicTitle }

        // Populate UI elements with topic details
        topic?.let {
            view.findViewById<TextView>(R.id.tvTopicTitle).text = it.title
            view.findViewById<TextView>(R.id.tvDescription).text = it.desc
            view.findViewById<TextView>(R.id.tvTotalQuestions).text = getString(R.string.total_questions, it.questions.size)
        }

        // Set up Begin button to start quiz with selected topic
        view.findViewById<Button>(R.id.btnBegin).setOnClickListener {
            val bundle = Bundle().apply {
                putString("topic", topicTitle)  // Pass the topic title to QuizQuestionFragment
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
