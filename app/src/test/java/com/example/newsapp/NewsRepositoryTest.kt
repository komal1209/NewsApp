package com.example.newsapp

import com.example.newsapp.data.api.ApiService
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest {

    private lateinit var repository: NewsRepository
    private val apiService: ApiService = mockk() // Mock API Service

    @Before
    fun setup() {
        repository = NewsRepository(apiService)
    }

    @Test
    fun `getNews() returns expected NewsResponse`() = runBlocking {
        // Arrange: Create sample data
        val sampleArticles = listOf(
            NewsArticle("Title 1", "Description 1", "url1", "image1", "Author 1"),
            NewsArticle("Title 2", "Description 2", "url2", "image2", "Author 2")
        )
        val response = NewsResponse(sampleArticles)

        // Mock API call response
        coEvery { apiService.getTopHeadlines(country ="us",
            category="business",apiKey="test_api_key") } returns response

        // Act: Call repository function
        val result = repository.getNews("test_api_key")

        // Assert: Verify response is correct
        assertEquals(response, result)
    }
}