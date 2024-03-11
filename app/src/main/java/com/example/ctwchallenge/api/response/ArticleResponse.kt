package com.example.ctwchallenge.api.response

import com.example.ctwchallenge.data.Article

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>,
)