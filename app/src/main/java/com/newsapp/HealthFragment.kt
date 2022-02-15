package com.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_health.*
import kotlinx.android.synthetic.main.fragment_sports.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HealthFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getNews()
    }

    private fun getNews() {
        val news = NewsApi.NewsService.newsInstance.getCategoryHeadlines("in", "health", 1, 25)
        news.enqueue(object : Callback<MainNews?> {
            override fun onResponse(call: Call<MainNews?>, response: Response<MainNews?>) {
                val news = response.body()
                if (news != null) {
                    Log.d("Hello", news.toString())
                    newsAdapter = NewsAdapter(
                        this@HealthFragment,
                        news.articles as ArrayList<Articles>
                    )
                    healthRecyclerView.adapter = newsAdapter
                    healthRecyclerView.layoutManager = LinearLayoutManager(activity)
                    newsAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MainNews?>, t: Throwable) {
                Log.d("Hello", "Error in fetching", t)
            }
        })

    }
}