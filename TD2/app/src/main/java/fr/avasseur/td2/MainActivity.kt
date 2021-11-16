package fr.avasseur.td2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.repas).setOnClickListener {
            Log.i("app-activity", "Start RepasActivity")
            val intent = Intent(this, RepasActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.meteo).setOnClickListener {
            Log.i("app-activity", "Start MeteoActivity")
            val intent = Intent(this, MeteoActivity::class.java)
            startActivity(intent)
        }
    }
}