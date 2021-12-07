package fr.avasseur.tp1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var activityId = R.id.action_popular
    lateinit var movieLoader: MovieLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieLoader = MovieLoader(this)

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                //  you will get result here in result.data
                val searchingText = (result.data as Intent).getStringExtra("result")
                Toast.makeText(this, searchingText.toString(), Toast.LENGTH_SHORT).show()
                if (searchingText != null) {
                    movieLoader.showSearchResults(searchingText)
                }
                return@registerForActivityResult;
            }
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation).selectedItemId = R.id.action_popular
        }

        findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation)?.setOnItemSelectedListener {

            activityId = it.itemId
            movieLoader.refresh(activityId, startForResult)

            return@setOnItemSelectedListener true
        }

        movieLoader.refresh(activityId, startForResult)

    }


}