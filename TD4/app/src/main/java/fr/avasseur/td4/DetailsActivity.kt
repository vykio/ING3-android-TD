package fr.avasseur.td4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        title = "Details"

        findViewById<TextView>(R.id.details_username_text).text =
            (applicationContext as NewsListApplication).username.toString()
    }
}