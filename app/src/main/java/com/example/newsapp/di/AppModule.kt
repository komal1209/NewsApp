package com.example.newsapp.di

import com.example.newsapp.data.api.ApiService
import com.example.newsapp.data.api.NewsInfoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val LIKES_COMMENTS_BASE_URL = "https://cn-news-info-api.herokuapp.com/"

    @Provides
    @Singleton
    @Named("NewsRetrofit")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("NewsApiService")
    fun provideApiService(@Named("NewsRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    @Named("LikesCommentsRetrofit")
    fun provideLikesCommentsRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LIKES_COMMENTS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    @Named("LikesCommentsApiService")
    fun provideNewsInfoApiService(@Named("LikesCommentsRetrofit")retrofit: Retrofit): NewsInfoApiService {
        return retrofit.create(NewsInfoApiService::class.java)
    }
}