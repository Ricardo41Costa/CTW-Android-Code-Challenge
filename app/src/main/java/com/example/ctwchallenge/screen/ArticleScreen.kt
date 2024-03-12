package com.example.ctwchallenge.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwchallenge.data.Article
import com.example.ctwchallenge.state.ArticleViewModel
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme

@Composable
fun ArticleScreen(article: Article, modifier: Modifier) {

}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    CTWChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            //val articles: ArrayList<Article> = populateArticles()
            //ArticleScreen(articles, Modifier)
        }
    }
}