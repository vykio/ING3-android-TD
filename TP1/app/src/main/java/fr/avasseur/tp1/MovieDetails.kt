package fr.avasseur.tp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import fr.avasseur.tp1.models.Movie
import fr.avasseur.tp1.services.TheMovieDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetails : AppCompatActivity() {

    lateinit var loadProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_movie_details)
        loadProgress = findViewById(R.id.progressBar)

        if (!intent.hasExtra("id")) {
            finish()
        }

        val movie_id = intent.getIntExtra("id", 0)

        val service: TheMovieDbService = Retrofit.Builder()
            .baseUrl(TheMovieDbService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<TheMovieDbService>(TheMovieDbService::class.java)

        service.getMovieDetails(movie_id, "1d67ab962871976756067abc79c7e399")?.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                val movie = response.body() as Movie

                var movieImageElement = findViewById<ImageView>(R.id.movie_image)
                Glide.with(movieImageElement).load("https://image.tmdb.org/t/p/original/" + movie.backdrop_path).into(movieImageElement)

                findViewById<TextView>(R.id.movie_title).text = movie.title
                loadProgress.visibility = View.GONE
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Toast.makeText(this@MovieDetails, "Impossible de charger les données", Toast.LENGTH_LONG).show()
                loadProgress.visibility = View.GONE
            }

        })

    }
}