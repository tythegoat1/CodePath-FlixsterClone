package com.example.codepath_flixsterclone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val Detail_URL = "https://api.themoviedb.org/3/movie/${MovieID}?api_key="
private const val SIM_URL = "https://api.themoviedb.org/3/movie/${MovieID}/similar?api_key="
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"


class DetailFragment : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_list, container, false)
        val progressBar = view.findViewById<View>(R.id.Detailprogress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.moviePosterRV) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY


        // Using the client, perform the HTTP request
        client [
                "${Detail_URL}${API_KEY}&language=en-US&page=1",
                params,
                object: JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        progressBar.hide()

                        // Look for this in Logcat:
                        Log.d("DetailFragment", json.toString())


                        val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                        val moviesRawJSON : String = resultsJSON.toString()

                        //Gson
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<CurrentMovie>>() {}.type

                        val models : List<CurrentMovie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                        recyclerView.adapter = DetailRecyclerViewAdapter(models,this@DetailFragment)

                    }
                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MovieFragment", errorResponse)
                        }
                    }
                }]
    }