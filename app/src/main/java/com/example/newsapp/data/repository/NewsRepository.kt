package com.example.newsapp.data.repository

import com.example.newsapp.data.api.ApiService
import com.example.newsapp.data.api.NewsInfoApiService
import com.example.newsapp.data.model.NewsResponse
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Named

class NewsRepository @Inject constructor(
    @Named("NewsApiService") private val apiService: ApiService,
    @Named("LikesCommentsApiService") private val newsInfoApiService: NewsInfoApiService
) {
    suspend fun getNews(apiKey: String): NewsResponse {
        return apiService.getTopHeadlines(apiKey = apiKey)
    }
    suspend fun getLikes(articleId: String): Int {
        return try {
            newsInfoApiService.getLikes(articleId).count
        } catch (e: HttpException) {
            if (e.code() == 404) {
                0
            } else {
                throw e
            }
        } catch (e: Exception) {
            0
        }
    }

    suspend fun getComments(articleId: String): Int {
        return try {
            newsInfoApiService.getComments(articleId).count
        } catch (e: HttpException) {
            if (e.code() == 404) {
                0
            } else {
                throw e
            }
        } catch (e: Exception) {
            0
        }
    }
}