package com.example.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    // Rules for LiveData & Coroutines
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: NewsViewModel
    private val repository: NewsRepository = mockk() // Mock Repository

    @Before
    fun setup() {
        viewModel = NewsViewModel(repository)
    }

    @Test
    fun `fetchNews() updates newsList correctly`() = runBlockingTest {
        // Arrange: Create sample data
        val sampleArticles = listOf(
            NewsArticle("Title 1", "Description 1", "url1", "image1", "Author 1"),
            NewsArticle("Title 2", "Description 2", "url2", "image2", "Author 2")
        )
        val response = NewsResponse(sampleArticles)

        // Mock repository response
        coEvery { repository.getNews(any()) } returns response

        // Act: Call ViewModel function
        viewModel.fetchNews("dummy_api_key")

        // Assert: Verify that newsList is updated correctly
        assertEquals(sampleArticles, viewModel.newsList.value)
    }
}