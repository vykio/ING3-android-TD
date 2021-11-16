package fr.avasseur.td2

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class RepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.repas)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }
}