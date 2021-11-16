package fr.avasseur.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val DEFAULT_FACES = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instance des boutons
        val rollButton = findViewById<Button>(R.id.rollButton)
        val nbFacesElement = findViewById<EditText>(R.id.nbFaces)

        // Nombre de faces par défaut
        var faces = DEFAULT_FACES;

        // Définir le nombre de faces par défaut
        nbFacesElement.setText(faces.toString())

        // onClickListener du bouton
        rollButton.setOnClickListener {
            // Définir le texte de la textView comme étant le random converti en String
            findViewById<TextView>(R.id.textView1).text = getRandom(faces).toString()
            findViewById<TextView>(R.id.textView2).text = getRandom(faces).toString()
        }

        // Listener de changement de texte
        nbFacesElement.addTextChangedListener(object : TextWatcher {

            // Fonction appelée après que le texte ait changé
            override fun afterTextChanged(s: Editable?) {
                // Définir le nombre de faces par ce que l'on veut, ou (si c'est nul ou vide)
                // par le nombre de faces par défaut
                faces = if (!s.isNullOrEmpty()) Integer.parseInt(s.toString()) else DEFAULT_FACES;
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun getRandom(nbFaces: Int): Int {
        // Retourner un entier aléatoire entre 1 et nbFaces (compris)
        return (1..nbFaces).random()
    }
}