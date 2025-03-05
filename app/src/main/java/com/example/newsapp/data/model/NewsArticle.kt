package com.example.newsapp.data.model

data class NewsArticle(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val author: String?
)

data class NewsResponse(
    val articles: List<NewsArticle>
)