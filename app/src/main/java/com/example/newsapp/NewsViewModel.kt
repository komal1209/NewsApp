package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsArticle>>()
    val newsList: LiveData<List<NewsArticle>> = _newsList

    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            val response = repository.getNews(apiKey)
            _newsList.postValue(response.articles)
        }
    }

    private val _likes = MutableStateFlow(0)
    val likes: StateFlow<Int> = _likes

    private val _comments = MutableStateFlow(0)
    val comments: StateFlow<Int> = _comments

    private val _errorMessage = MutableStateFlow<String?>(null) // ✅ Error message
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchLikes(articleId: String) {
        viewModelScope.launch {
            try {
                _likes.value = repository.getLikes(articleId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load likes"
            }
        }
    }

    fun fetchComments(articleId: String) {
        viewModelScope.launch {
            try {
                _comments.value = repository.getComments(articleId)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load comments"
            }
        }
    }
}