package com.newsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "c56fcffe8b8e47ab9e3cf681522aeef3"

interface NewsApi {

    @GET("top-headlines?apiKey=$API_KEY")
    fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<MainNews>

    @GET("top-headlines?apiKey=$API_KEY")
    fun getCategoryHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<MainNews>

    object NewsService {
        val newsInstance: NewsApi

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsInstance = retrofit.create(NewsApi::class.java)
        }
    }
}