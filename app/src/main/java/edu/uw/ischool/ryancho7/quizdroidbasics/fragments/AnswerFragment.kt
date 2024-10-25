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

        // Retrieve data from arguments
        val selectedAnswerIndex = arguments?.getInt("selectedAnswerIndex") ?: -1
        val correctAnswerIndex = arguments?.getInt("correctAnswerIndex") ?: -1
        val correctAnswers = arguments?.getInt("correctAnswers") ?: 0
        val totalQuestions = arguments?.getInt("totalQuestions") ?: 0
        val answersList = arguments?.getStringArray("answerOptions") ?: emptyArray()

        isLastQuestion = arguments?.getBoolean("isLastQuestion") ?: false

        // Display the selected answer and correct answer
        view.findViewById<TextView>(R.id.tvSelectedAnswer).text =
            "You selected: ${answersList[selectedAnswerIndex]}"
        view.findViewById<TextView>(R.id.tvCorrectAnswer).text =
            "Correct answer: ${answersList[correctAnswerIndex]}"

        // Display the score
        view.findViewById<TextView>(R.id.tvScore).text =
            "You have $correctAnswers out of $totalQuestions correct"

        // Handle Next/Finish button
        val nextButton = view.findViewById<Button>(R.id.btnNext)
        if (isLastQuestion) {
            nextButton.text = "Finish"
            nextButton.setOnClickListener {
                // Pop back to the topic list using the "TopicList" tag
                parentFragmentManager.popBackStack("TopicList", 0)  // Return to topic list
            }
        } else {
            nextButton.text = "Next"
            nextButton.setOnClickListener {
                parentFragmentManager.popBackStack()  // Pop back to QuizQuestionFragment
            }
        }
    }
}
