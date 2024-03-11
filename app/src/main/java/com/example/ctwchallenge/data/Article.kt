package com.example.ctwchallenge.data

import android.net.Uri

data class Article(
    val author: String,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    var pathToImage: String?,
)