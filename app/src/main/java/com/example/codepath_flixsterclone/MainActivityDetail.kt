package com.example.codepath_flixsterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        detailCardView()
        loadPostImage()

    }

    fun detailCardView() {
        val titleTV = findViewById<TextView>(R.id.movieDetailTitle)
        val descriptionTV = findViewById<TextView>(R.id.movieDetailDescription)

        val detailTitle = intent.getStringExtra("title").toString()
        val detailDescription = intent.getStringExtra("description").toString()

        titleTV.text = detailTitle
        descriptionTV.text = detailDescription

    }

    fun loadPostImage() {

        val detailIV : ImageView = findViewById(R.id.movieDetailImage)
        val posterID = intent.getStringExtra("posterID")


        Picasso.get().load("https://api.themoviedb.org/3/movie/"
                +posterID+"/images?api-key=${API_KEY}")
            .resize(200, 300)
            .centerCrop()
            .into(detailIV)
    }
}

