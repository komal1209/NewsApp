package com.example.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.newsapp.ui.theme.NewsAppTheme

import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.newsapp.NewsViewModel
import com.example.newsapp.ui.screens.newsdetails.NewsDetailScreen

@AndroidEntryPoint
class NewsDetailActivity : ComponentActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("news_title") ?: "No Title"
        val description = intent.getStringExtra("news_description") ?: "No Description"
        val imageUrl = intent.getStringExtra("news_image")
        val newsUrl = intent.getStringExtra("news_url") ?: ""

        val articleId = convertUrlToArticleId(newsUrl)

        // Fetch likes and comments
        viewModel.fetchLikes(articleId)
        viewModel.fetchComments(articleId)

        setContent {
            NewsAppTheme {
                NewsDetailScreen(title, description, imageUrl, viewModel)
            }
        }
    }

    private fun convertUrlToArticleId(url: String): String {
        return url.replace("https://", "")
            .replace("/", "-")
    }
}


