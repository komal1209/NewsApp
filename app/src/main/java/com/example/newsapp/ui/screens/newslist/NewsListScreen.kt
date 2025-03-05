package com.example.newsapp.ui.screens.newslist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.NewsViewModel
import com.example.newsapp.data.model.NewsArticle


@Composable
fun NewsListScreen(viewModel: NewsViewModel, onNewsClick: (NewsArticle) -> Unit, modifier: Modifier = Modifier) {
    val newsList by viewModel.newsList.observeAsState()

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(newsList ?: emptyList()) { article ->
            NewsItem(article,onClick = { onNewsClick(article) })
        }
    }
}