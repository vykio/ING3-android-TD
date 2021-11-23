package fr.avasseur.td6.interfaces

import fr.avasseur.td6.models.Repo
import fr.avasseur.td6.models.RepoList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call


interface GithubService {

    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?

    @GET("/search/repositories")
    fun searchRepos(@Query("q") query: String?): Call<RepoList?>? // Pour le POST, remplacer @Query par @Field et mettre @POST au lieu de @GET

    companion object {
        val ENDPOINT: String = "https://api.github.com"
    }
}