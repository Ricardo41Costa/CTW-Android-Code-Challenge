package com.example.ctwchallenge.api

import com.example.ctwchallenge.api.response.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String): Call<ArticleResponse>
}