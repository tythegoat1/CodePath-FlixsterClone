package com.example.codepath_flixsterclone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter (
     private val currentMovieList: List<CurrentMovie>,
     private val mListener: OnListFragmentInteractionListener?
     )
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
{

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_row, parent, false)
         // Return a new holder instance
         return MovieViewHolder(view)
     }

    inner class MovieViewHolder(val mView: View) : ViewHolder(mView) {
            val movieTitleViewHolder: TextView = mView.findViewById(R.id.movieTitleTV) as TextView
            val movieDescriptionViewHolder: TextView = mView.findViewById(R.id.movieDescriptionTV) as TextView
            val movieImageViewHolder: ImageView = mView.findViewById(R.id.movieImageIV) as ImageView
    }

     override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
         // Get the data model based on position
         val currentMovie = currentMovieList[position]
         // Set item views based on views and data model
         holder.movieTitleViewHolder.text = currentMovie.movieTitleModel
         holder.movieDescriptionViewHolder.text = currentMovie.movieDescriptionModel

         Glide.with(holder.mView)
             .load(currentMovie.movieImageModel)
             .centerInside()
             .into(holder.movieImageViewHolder)
     }

     override fun getItemCount(): Int {
         return currentMovieList.size
     }
 }


