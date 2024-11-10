package edu.uw.ischool.ryancho7.quizdroidbasics

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.ryancho7.quizdroidbasics.fragments.TopicListFragment
import edu.uw.ischool.ryancho7.quizdroidbasics.repository.TopicRepository
import edu.uw.ischool.ryancho7.quizdroidbasics.repository.TopicRepositoryImpl
import edu.uw.ischool.ryancho7.quizdroidbasics.ui.PreferencesActivity

class MainActivity : AppCompatActivity() {
    private lateinit var topicRepository: TopicRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topicRepository = (application as QuizApp).topicRepository

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TopicListFragment())
                .addToBackStack("TopicList")
                .commit()
        }

        if (!isConnected()) {
            showNoConnectionDialog()
        } else {
            initializeTopicRepo()
        }
    }

private fun initializeTopicRepo() {
    (topicRepository as? TopicRepositoryImpl)?.let { repo ->
        repo.setOnDownloadCompleteListener(object : TopicRepositoryImpl.OnDownloadCompleteListener {
            override fun onDownloadComplete(success: Boolean, message: String) {
                runOnUiThread {
                    if (success) {
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    } else {
                        showRetryDialog(message)
                    }
                }
            }
        })
        repo.downloadJsonInBackground()
    }
}

    private fun showNoConnectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection")
        if (isAirplaneModeOn()) {
            builder.setMessage("Airplane mode is on. Would you like to turn it off?")
                .setPositiveButton("Go to Settings") { _, _ ->
                    startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        } else {
            builder.setMessage("No internet connection available.")
                .setPositiveButton("Retry") { _, _ ->
                    if (isConnected()) {
                        initializeTopicRepo()
                    } else {
                        Toast.makeText(this, "Still no connection.", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Quit") { _, _ -> finish() }
        }
        builder.show()
    }

    private fun isConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun isAirplaneModeOn(): Boolean {
        return Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    }

    fun showRetryDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Download Failed")
        builder.setMessage("$message. Do you want to retry?")
            .setPositiveButton("Retry") { _, _ -> initializeTopicRepo() }
            .setNegativeButton("Quit") { _, _ -> finish() }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_preferences -> {
                startActivity(Intent(this, PreferencesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}