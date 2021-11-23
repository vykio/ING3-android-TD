package fr.avasseur.td6.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.avasseur.td6.R
import fr.avasseur.td6.models.Repo

class RepoAdapter(repos: List<Repo>, private val listener: (Repo) -> Unit) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    var mRepos: List<Repo> = repos

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var repoNameElement = itemView.findViewById<TextView>(R.id.repoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = mRepos[position].full_name?.split("/")

        holder.repoNameElement.text = Html.fromHtml((text?.get(0) ?: "ERROR") + "/<b>" + (text?.get(1)
            ?: "ERROR") + "</b>", Html.FROM_HTML_MODE_COMPACT)
        holder.repoNameElement.setOnClickListener { listener(mRepos[position]) }
    }

    override fun getItemCount(): Int {
        return mRepos.size
    }

}