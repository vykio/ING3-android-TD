package fr.avasseur.tp1.services

import fr.avasseur.tp1.models.Movie
import fr.avasseur.tp1.models.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("/3/movie/popular")
    fun listPopularMovies(@Query("api_key") api_key: String): Call<MovieList?>?

    @GET("/3/movie/upcoming")
    fun listUpcomingMovies(@Query("api_key") api_key: String): Call<MovieList?>?

    @GET("/3/movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") api_key: String): Call<Movie?>?

    @GET("/3/search/movie")
    fun searchMovies(@Query("api_key") api_key: String, @Query("query") query: String): Call<MovieList?>?

    companion object {
        val ENDPOINT: String = "https://api.themoviedb.org/"
    }
}