package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import edu.uw.ischool.ryancho7.quizdroidbasics.R

class AnswerFragment : Fragment() {

    private var isLastQuestion = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedAnswerIndex = arguments?.getInt("selectedAnswerIndex") ?: -1
        val correctAnswerIndex = arguments?.getInt("correctAnswerIndex") ?: -1
        val correctAnswers = arguments?.getInt("correctAnswers") ?: 0
        val totalQuestions = arguments?.getInt("totalQuestions") ?: 0
        val answersList = arguments?.getStringArray("answerOptions") ?: emptyArray()

        isLastQuestion = arguments?.getBoolean("isLastQuestion") ?: false

        view.findViewById<TextView>(R.id.tvSelectedAnswer).text =
            getString(R.string.you_selected, answersList[selectedAnswerIndex])
        view.findViewById<TextView>(R.id.tvCorrectAnswer).text =
            getString(R.string.correct_answer, answersList[correctAnswerIndex])
        view.findViewById<TextView>(R.id.tvScore).text =
            getString(R.string.your_score, correctAnswers, totalQuestions)

        // Handle Next/Finish button
        val nextButton = view.findViewById<Button>(R.id.btnNext)
        if (isLastQuestion) {
            nextButton.text = getString(R.string.finish)
            nextButton.setOnClickListener {
                // Pop back to the topic list using the "TopicList" tag
                parentFragmentManager.popBackStack("TopicList", 0)  // Return to topic list
            }
        } else {
            nextButton.text = getString(R.string.next)
            nextButton.setOnClickListener {
                parentFragmentManager.popBackStack()  // Pop back to QuizQuestionFragment
            }
        }
    }
}
