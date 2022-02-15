package com.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getNews()
    }

    private fun getNews() {
        val news = NewsApi.NewsService.newsInstance.getHeadlines("in", 1, 25)
        news.enqueue(object : Callback<MainNews?> {
            override fun onResponse(call: Call<MainNews?>, response: Response<MainNews?>) {
                val news = response.body()
                if (news != null) {
                    Log.d("Hello", news.toString())
                    newsAdapter = NewsAdapter(
                        this@HomeFragment,
                        news.articles as ArrayList<Articles>
                    )
                    homeRecyclerView.layoutManager = LinearLayoutManager(activity)
                    homeRecyclerView.adapter = newsAdapter
                    newsAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MainNews?>, t: Throwable) {
                Log.d("Hello", "Error in fetching", t)
            }
        })
    }
}