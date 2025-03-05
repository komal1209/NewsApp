package com.example.newsapp.ui.screens.newsdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.NewsViewModel
import com.example.newsapp.R

@Composable
fun NewsDetailScreen(title: String, description: String, imageUrl: String?, viewModel: NewsViewModel) {
    val likes by viewModel.likes.collectAsState()
    val comments by viewModel.comments.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = if (imageUrl.isNullOrEmpty()) {
                painterResource(R.drawable.image_placeholder)
            } else {
                rememberAsyncImagePainter(imageUrl)
            },
            contentDescription = "News Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))


        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }


        Text(
            text = "Likes: $likes",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Comments: $comments",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}