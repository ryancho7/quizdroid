

package edu.uw.ischool.ryancho7.quizdroidbasics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import edu.uw.ischool.ryancho7.quizdroidbasics.QuizApp
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

        // load topics from the repository and display them
        loadTopics(view)
    }

    private fun loadTopics(view: View) {
        // Access TopicRepository from QuizApp
        val repository = (requireActivity().application as QuizApp).topicRepository
        val topics = repository.getAllTopics()

        // Reference to the layout where topic buttons will be added
        val layout = view.findViewById<LinearLayout>(R.id.topic_buttons_layout)

        // Dynamically create buttons for each topic
        topics.forEach { topic ->
            val button = Button(requireContext()).apply {
                text = topic.title
                setOnClickListener {
                    openTopicOverview(topic.title)  // Pass the topic title when button is clicked
                }
            }
            layout.addView(button)
        }
    }

    private fun openTopicOverview(topicTitle: String) {
        val bundle = Bundle().apply {
            putString("topic", topicTitle)  // Pass the topic title to TopicOverviewFragment
        }
        val fragment = TopicOverviewFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

