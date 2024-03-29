package com.example.ctwchallenge.data

import android.os.Bundle

data class Article(
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
)