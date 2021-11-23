package fr.avasseur.td6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import fr.avasseur.td6.interfaces.GithubService

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import fr.avasseur.td6.models.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service: GithubService = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<GithubService>(GithubService::class.java)

        service.listRepos("vykio")?.enqueue(object : Callback<List<Repo?>?> {
            override fun onResponse(call: Call<List<Repo?>?>?, response: Response<List<Repo?>?>) {
                response.body()?.let { afficherRepos(it) }
            }

            override fun onFailure(call: Call<List<Repo?>?>?, t: Throwable?) {}
        })

        findViewById<Button>(R.id.searchButton).setOnClickListener {
            val repo = findViewById<EditText>(R.id.searchRepos).text
            if (repo.isNotEmpty()) {
                var intent: Intent = Intent(this, RepoSearchActivity::class.java)
                intent.putExtra("repoName", repo.toString())
                startActivity(intent)
            }
        }

    }

    fun afficherRepos(repos: List<Repo?>) {
        Toast.makeText(this, "Github.com/vykio - Nombre de dépots : " + repos.size, Toast.LENGTH_SHORT).show()
    }
}