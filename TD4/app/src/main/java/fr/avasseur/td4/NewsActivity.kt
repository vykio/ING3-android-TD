package fr.avasseur.td4

import android.content.Intent
import android.net.Uri.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        title = "News"

        val username = (applicationContext as NewsListApplication).username.toString()
        findViewById<TextView>(R.id.username_text).text = username

        findViewById<Button>(R.id.details_button).setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.logout_button).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.about_button).setOnClickListener {
            val url = "https://news.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, parse(url))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}