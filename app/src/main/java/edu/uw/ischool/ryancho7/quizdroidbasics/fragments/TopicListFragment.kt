

package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.uw.ischool.ryancho7.quizdroidbasics.R

class TopicListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_topic_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up buttons for each topic
        view.findViewById<Button>(R.id.btnMath).setOnClickListener {
            openQuiz("Math")
        }
        view.findViewById<Button>(R.id.btnPhysics).setOnClickListener {
            openQuiz("Physics")
        }
        view.findViewById<Button>(R.id.btnMarvel).setOnClickListener {
            openQuiz("Marvel Super Heroes")
        }
        view.findViewById<Button>(R.id.btnGhibli).setOnClickListener {
            openQuiz("Studio Ghibli")
        }
        view.findViewById<Button>(R.id.btnMario).setOnClickListener {
            openQuiz("Mario")
        }
        view.findViewById<Button>(R.id.btnHPotter).setOnClickListener {
            openQuiz("Harry Potter")
        }
    }

    // Navigate to the QuizQuestionFragment with the selected topic
    private fun openQuiz(topic: String) {
        val bundle = Bundle().apply {
            putString("topic", topic)
        }
        val fragment = TopicOverviewFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

