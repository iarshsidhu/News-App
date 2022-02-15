package com.newsapp

data class MainNews(
    val status: String,
    val totalResults: Int,
    val articles : List<Articles>
)
