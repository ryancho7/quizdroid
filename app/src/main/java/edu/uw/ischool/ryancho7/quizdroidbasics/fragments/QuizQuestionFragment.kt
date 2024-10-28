package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import edu.uw.ischool.ryancho7.quizdroidbasics.R
import edu.uw.ischool.ryancho7.quizdroidbasics.data.QuizData
import edu.uw.ischool.ryancho7.quizdroidbasics.models.Question

class QuizQuestionFragment : Fragment() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var isReturningFromAnswer = false
    private val answerHistory = mutableListOf<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the selected topic from arguments
        val topic = arguments?.getString("topic") ?: getString(R.string.topic_math)

        // Load the questions for the selected topic
        questions = when (topic) {
            getString(R.string.topic_math) -> QuizData.getMathQuestions()
            getString(R.string.topic_physics) -> QuizData.getPhysicsQuestions()
            getString(R.string.topic_marvel) -> QuizData.getMarvelQuestions()
            getString(R.string.topic_ghibli) -> QuizData.getStudioGhibliQuestions()
            getString(R.string.topic_mario) -> QuizData.getMarioQuestions()
            getString(R.string.topic_hp) -> QuizData.getHarryPotterQuestions()
            else -> QuizData.getMathQuestions()
        }

        // If returning from the answer page, load the next question
        if (isReturningFromAnswer) {
            proceedToNextQuestion()
        } else {
            // Load the current question normally
            loadQuestion(view)
        }

        // Set up the Submit button functionality
        val submitButton = view.findViewById<Button>(R.id.btnSubmit)
        submitButton.isEnabled = false
        view.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }

        submitButton.setOnClickListener {
            navigateToAnswerPage(view)
        }

        // Add callback to handle back button presses
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed() // Call the back press handler
            }
        })
    }

    private fun loadQuestion(view: View) {
        val question = questions[currentQuestionIndex]
        view.findViewById<TextView>(R.id.tvQuestion).text = question.questionText
        view.findViewById<RadioButton>(R.id.radioOption1).text = question.options[0]
        view.findViewById<RadioButton>(R.id.radioOption2).text = question.options[1]
        view.findViewById<RadioButton>(R.id.radioOption3).text = question.options[2]
        view.findViewById<RadioButton>(R.id.radioOption4).text = question.options[3]
    }

    private fun navigateToAnswerPage(view: View) {
        val selectedOptionId = view.findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
        val selectedAnswerIndex = when (selectedOptionId) {
            R.id.radioOption1 -> 0
            R.id.radioOption2 -> 1
            R.id.radioOption3 -> 2
            R.id.radioOption4 -> 3
            else -> -1
        }

        // check answer
        val isCorrect = selectedAnswerIndex == questions[currentQuestionIndex].correctAnswer
        if(isCorrect) {
            correctAnswers++
        }
        answerHistory.add(isCorrect)

        // Prepare the answer options list to pass to the AnswerFragment
        val answerOptions = questions[currentQuestionIndex].options.toTypedArray()

        // Pass data to the AnswerFragment
        val bundle = Bundle().apply {
            putInt("selectedAnswerIndex", selectedAnswerIndex)
            putInt("correctAnswerIndex", questions[currentQuestionIndex].correctAnswer)
            putInt("correctAnswers", correctAnswers)
            putInt("totalQuestions", questions.size)
            putStringArray("answerOptions", answerOptions) // Pass answer options
            putBoolean("isLastQuestion", currentQuestionIndex == questions.size - 1)
        }

        // Navigate to AnswerFragment
        val fragment = AnswerFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("AnswerPage")
            .commit()
    }


    private fun handleBackPressed() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            if(answerHistory.isNotEmpty()) {
                val lastAnswer = answerHistory.removeAt(answerHistory.size - 1)
                if(lastAnswer) {
                    correctAnswers--
                }
            }
            loadQuestion(requireView()) // Load the previous question
        } else {
            parentFragmentManager.popBackStack("TopicList", 0) // Return to Topic List
        }
    }

    fun proceedToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion(requireView())
            isReturningFromAnswer = false // Reset the flag
        } else {
            // If finished, navigate to the score or topic list
            parentFragmentManager.popBackStack("TopicList", 0)
        }
    }

    override fun onResume() {
        super.onResume()
        // Check if returning from the answer page and set flag
        isReturningFromAnswer = true
    }
}

