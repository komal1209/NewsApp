package com.example.newsapp.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface NewsInfoApiService {
    @GET("likes/{articleId}")
    suspend fun getLikes(@Path("articleId") articleId: String): LikeResponse

    @GET("comments/{articleId}")
    suspend fun getComments(@Path("articleId") articleId: String): CommentResponse
}


data class LikeResponse(val count: Int)
data class CommentResponse(val count: Int)