package fr.avasseur.td4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login";

        val login_button = findViewById<Button>(R.id.login_button)

        login_button.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            val usernameElement = findViewById<EditText>(R.id.username)

            if (usernameElement.text.isNotEmpty()) {
                (applicationContext as NewsListApplication).username = usernameElement.text.toString()
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Veuillez saisir le login", Toast.LENGTH_SHORT).show()
            }
        }
    }
}