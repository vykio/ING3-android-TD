package fr.avasseur.tp1.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.avasseur.tp1.R
import fr.avasseur.tp1.models.Movie

class MovieAdapter(movies: List<Movie>, private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var mMovies: List<Movie> = movies

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieNameElement = itemView.findViewById<TextView>(R.id.movieName)
        var movieElementLayout = itemView.findViewById<ConstraintLayout>(R.id.movieElementLayout)
        var imageView: ImageView = itemView.findViewById(R.id.imageView) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = mMovies[position].title

        holder.movieNameElement.text = text
        holder.movieNameElement.setOnClickListener { listener(mMovies[position]) }
        holder.movieElementLayout.setOnClickListener { listener(mMovies[position]) }

        Glide.with(holder.imageView).load("https://image.tmdb.org/t/p/w500/" + mMovies[position].poster_path).apply(
            RequestOptions().override(200, 600)).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }
}