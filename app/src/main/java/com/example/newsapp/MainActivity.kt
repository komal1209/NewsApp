package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.newsapp.ui.NewsDetailActivity
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.screens.newslist.NewsListScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchNews("f57a02b149ab4d34be545eb9e08545b2") // Fetch news on start

        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsListScreen(viewModel,
                        onNewsClick = { article ->
                            val intent = Intent(this, NewsDetailActivity::class.java).apply {
                                putExtra("news_title", article.title)
                                putExtra("news_description", article.description)
                                putExtra("news_image", article.urlToImage)
                                putExtra("news_url", article.url)
                            }
                            Log.d("MainActivity ",intent.toString())
                            startActivity(intent)
                        }, Modifier.padding(innerPadding))
                }
            }
        }
    }
}