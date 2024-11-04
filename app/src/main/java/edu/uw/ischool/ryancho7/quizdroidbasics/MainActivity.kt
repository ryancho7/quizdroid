package edu.uw.ischool.ryancho7.quizdroidbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.ischool.ryancho7.quizdroidbasics.fragments.TopicListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TopicListFragment())
                .addToBackStack("TopicList")  // Add to back stack with "TopicList" tag
                .commit()
        }
    }
}