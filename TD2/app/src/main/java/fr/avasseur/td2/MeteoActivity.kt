package fr.avasseur.td2

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.text.SimpleDateFormat


class MeteoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meteo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dateTextbox = findViewById<EditText>(R.id.datePickerTextbox)
        val timeTextbox = findViewById<EditText>(R.id.timePickerTextbox)
        val spinner     = findViewById<Spinner>(R.id.skyState)

        dateTextbox.setOnClickListener {
            var calendar = Calendar.getInstance();
            DatePickerDialog(this,
                { _, year, month, day ->
                    dateTextbox.setText(day.toString() + "/" + (month + 1) + "/" + year)
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        timeTextbox.setOnClickListener {
            var calendar = Calendar.getInstance()
            TimePickerDialog(this,
                { _, hour, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    timeTextbox.setText(SimpleDateFormat("HH:mm").format(calendar.time))
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
            ).show()
        }

        ArrayAdapter.createFromResource(this, R.array.skyStates, android.R.layout.simple_spinner_item)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = it
            }

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