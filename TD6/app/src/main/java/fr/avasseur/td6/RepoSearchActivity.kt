package fr.avasseur.td6

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.avasseur.td6.adapters.RepoAdapter
import fr.avasseur.td6.interfaces.GithubService
import fr.avasseur.td6.models.Repo
import fr.avasseur.td6.models.RepoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_search)

        if (!intent.hasExtra("repoName")) {
            finish()
        }

        val repoName = intent.getStringExtra("repoName")

        title = "Recherche: " + repoName

        val service: GithubService = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<GithubService>(GithubService::class.java)

        var recycler: RecyclerView = findViewById(R.id.reposRecyclerView)
        var repos: List<Repo> = ArrayList<Repo>()

        Log.i("azerty", repoName.toString())

        service.searchRepos(repoName.toString())?.enqueue(object : Callback<RepoList?> {
            override fun onResponse(call: Call<RepoList?>?, response: Response<RepoList?>) {
                repos = (response.body() as RepoList).items
                Log.i("azerty", "Hello")
                Log.i("azerty", repos.toString())
                recycler.adapter = RepoAdapter(repos) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url)))
                }
                recycler.layoutManager = LinearLayoutManager(this@RepoSearchActivity)
            }

            override fun onFailure(call: Call<RepoList?>?, t: Throwable?) {
                throw t!!;
            }
        })


    }
}