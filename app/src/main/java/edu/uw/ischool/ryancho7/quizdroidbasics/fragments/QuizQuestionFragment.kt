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
import edu.uw.ischool.ryancho7.quizdroidbasics.QuizApp
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

        // Retrieve questions from repository based on selected topic
        val topicTitle = arguments?.getString("topic") ?: ""
        val repository = (requireActivity().application as QuizApp).topicRepository
        questions = repository.getQuestionsForTopic(topicTitle)

        // Load the first question or continue if returning from the answer page
        if (isReturningFromAnswer) {
            proceedToNextQuestion()
        } else {
            loadQuestion(view)
        }

        setupSubmitButton(view)
        setupBackButton(view)
    }

    private fun loadQuestion(view: View) {
        val question = questions[currentQuestionIndex]
        view.findViewById<TextView>(R.id.tvQuestion).text = question.text
        view.findViewById<RadioButton>(R.id.radioOption1).text = question.answers[0]
        view.findViewById<RadioButton>(R.id.radioOption2).text = question.answers[1]
        view.findViewById<RadioButton>(R.id.radioOption3).text = question.answers[2]
        view.findViewById<RadioButton>(R.id.radioOption4).text = question.answers[3]
    }

    private fun setupSubmitButton(view: View) {
        val submitButton = view.findViewById<Button>(R.id.btnSubmit)
        submitButton.isEnabled = false
        view.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, _ ->
            submitButton.isEnabled = true
        }

        submitButton.setOnClickListener {
            navigateToAnswerPage(view)
        }
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

        // Check answer correctness
        val isCorrect = selectedAnswerIndex == questions[currentQuestionIndex].answer-1
        if (isCorrect) {
            correctAnswers++
        }
        answerHistory.add(isCorrect)

        // Prepare data to pass to the AnswerFragment
        val answerOptions = questions[currentQuestionIndex].answers.toTypedArray()
        val bundle = Bundle().apply {
            putInt("selectedAnswerIndex", selectedAnswerIndex)
            putInt("correctAnswerIndex", questions[currentQuestionIndex].answer-1)
            putInt("correctAnswers", correctAnswers)
            putInt("totalQuestions", questions.size)
            putStringArray("answerOptions", answerOptions)
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

    private fun setupBackButton(view: View) {
        // Handle back button press behavior
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        })
    }

    private fun handleBackPressed() {
        if (currentQuestionIndex > 0) {
            // Go back one question if possible
            currentQuestionIndex--
            if (answerHistory.isNotEmpty()) {
                val lastAnswer = answerHistory.removeAt(answerHistory.size - 1)
                if (lastAnswer) correctAnswers--
            }
            loadQuestion(requireView())
        } else {
            // If no previous question, return to Topic List
            parentFragmentManager.popBackStack("TopicList", 0)
        }
    }

    fun proceedToNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            loadQuestion(requireView())
            isReturningFromAnswer = false // Reset flag
        } else {
            // End of quiz; return to Topic List
            parentFragmentManager.popBackStack("TopicList", 0)
        }
    }

    override fun onResume() {
        super.onResume()
        // Check if returning from the answer page
        isReturningFromAnswer = true
    }
}
