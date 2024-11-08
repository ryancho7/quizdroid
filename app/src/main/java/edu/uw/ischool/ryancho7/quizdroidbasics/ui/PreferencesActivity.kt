package edu.uw.ischool.ryancho7.quizdroidbasics.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.ryancho7.quizdroidbasics.R

class PreferencesActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var frequencyEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        sharedPreferences = getSharedPreferences("quiz_preferences", Context.MODE_PRIVATE)

        urlEditText = findViewById(R.id.editTextUrl)
        frequencyEditText = findViewById(R.id.editTextFrequency)
        val saveButton: Button = findViewById(R.id.buttonSave)

        // Load existing preferences
        loadPreferences()

        saveButton.setOnClickListener {
            savePreferences()
        }
    }

    private fun loadPreferences() {
        val url = sharedPreferences.getString("data_url", "http://tednewardsandbox.site44.com/questions.json")
        val frequency = sharedPreferences.getInt("check_frequency", 1) // Default to 60 minutes

        if (url == null) {
            urlEditText.text = null
        } else {
            urlEditText.setText(url)
        }

        if (frequency != 0) {
            frequencyEditText.setText(frequency.toString())
        } else {
            frequencyEditText.text = null
        }
    }

    private fun savePreferences() {
        val url = urlEditText.text.toString()
        val frequency = frequencyEditText.text.toString().toIntOrNull()

        if (frequency == null || frequency <= 0) {
            Toast.makeText(this, "Please enter a valid frequency in minutes", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to SharedPreferences
        with(sharedPreferences.edit()) {
            putString("data_url", url)
            putInt("check_frequency", frequency)
            apply()
        }

        Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show()
        finish() // Close the activity
    }
}
