package fr.avasseur.tp1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        title = "Search"

        findViewById<Button>(R.id.searchButton).setOnClickListener {

            val text = findViewById<EditText>(R.id.editText).text

            if (text.isEmpty()) {
                return@setOnClickListener;
            }

            val returnString = Intent()
            returnString.putExtra("result", text.toString())
            setResult(Activity.RESULT_OK, returnString)
            finish()

        }

    }
}