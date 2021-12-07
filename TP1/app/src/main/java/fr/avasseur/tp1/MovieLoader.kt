package fr.avasseur.tp1

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.avasseur.tp1.adapters.MovieAdapter
import fr.avasseur.tp1.models.Movie
import fr.avasseur.tp1.models.MovieList
import fr.avasseur.tp1.services.TheMovieDbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieLoader {

    lateinit var service : TheMovieDbService
    lateinit var recycler: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var activity: MainActivity

    constructor(activity: MainActivity) {
        service = Retrofit.Builder()
            .baseUrl(TheMovieDbService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<TheMovieDbService>(TheMovieDbService::class.java)
        recycler = activity.findViewById(R.id.moviesRecyclerView)
        progressBar = activity.findViewById<ProgressBar>(R.id.mainProgressBar)
        this@MovieLoader.activity = activity

        recycler.layoutManager = GridLayoutManager(activity, 2)
    }

    fun refresh(activityId: Int, callback: ActivityResultLauncher<Intent>? = null) {
        var movies: List<Movie> = ArrayList<Movie>()

        loadingStarted()

        when(activityId) {
            R.id.action_popular -> {
                activity.title = "Popular Movies"
                service.listPopularMovies("1d67ab962871976756067abc79c7e399")?.enqueue(object :
                    Callback<MovieList?> {
                    override fun onResponse(call: Call<MovieList?>?, response: Response<MovieList?>) {
                        movies = (response.body() as MovieList).results
                        recycler.adapter = MovieAdapter(movies) {
                            var intent: Intent = Intent(activity, MovieDetails::class.java)
                            intent.putExtra("id", it.id)
                            activity.startActivity(intent)
                        }

                        loadingEnded()
                    }

                    override fun onFailure(call: Call<MovieList?>?, t: Throwable?) { }
                })
            }
            R.id.action_upcoming -> {
                activity.title = "Upcoming Movies"
                service.listUpcomingMovies("1d67ab962871976756067abc79c7e399")?.enqueue(object :
                    Callback<MovieList?> {
                    override fun onResponse(call: Call<MovieList?>?, response: Response<MovieList?>) {
                        movies = (response.body() as MovieList).results
                        recycler.adapter = MovieAdapter(movies) {
                            var intent: Intent = Intent(activity, MovieDetails::class.java)
                            intent.putExtra("id", it.id)
                            activity.startActivity(intent)
                        }
                        loadingEnded()
                    }

                    override fun onFailure(call: Call<MovieList?>?, t: Throwable?) { }
                })
            }
            R.id.action_search -> {
                var intent: Intent = Intent(activity, SearchActivity::class.java)
                if (callback != null) {
                    callback.launch(intent)
                }
                loadingEnded()
            }

        }

    }

    fun showSearchResults(searchingText: String)
    {
        var movies: List<Movie> = ArrayList<Movie>()

        loadingStarted()

        activity.title = "Searching : " + searchingText

        service.searchMovies("1d67ab962871976756067abc79c7e399", searchingText)?.enqueue(object: Callback<MovieList?> {
            override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {
                movies = (response.body() as MovieList).results

                if (movies.isEmpty()) {
                    Toast.makeText(activity, "Aucun résultat trouvé", Toast.LENGTH_LONG).show()
                    activity.findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation).selectedItemId = R.id.action_popular
                    refresh(R.id.action_popular)
                }

                recycler.adapter = MovieAdapter(movies) {
                    var intent: Intent = Intent(activity, MovieDetails::class.java)
                    intent.putExtra("id", it.id)
                    activity.startActivity(intent)
                }
                recycler.layoutManager = GridLayoutManager(activity, 2)
                loadingEnded()
            }

            override fun onFailure(call: Call<MovieList?>, t: Throwable) { }

        })

    }

    fun loadingStarted()
    {
        progressBar.visibility = View.VISIBLE
    }

    fun loadingEnded()
    {
        progressBar.visibility = View.INVISIBLE
    }

}