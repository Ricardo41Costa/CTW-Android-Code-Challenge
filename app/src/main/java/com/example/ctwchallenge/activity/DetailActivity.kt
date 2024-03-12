@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ctwchallenge.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.ctwchallenge.data.Article
import com.example.ctwchallenge.screen.ArticleScreen
import com.example.ctwchallenge.screen.ErrorScreen
import com.example.ctwchallenge.screen.HomeScreen
import com.example.ctwchallenge.screen.LoadingScreen
import com.example.ctwchallenge.state.ArticleViewModel
import com.example.ctwchallenge.state.UiState
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme
import com.example.ctwchallenge.widgets.AppBar

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTWChallengeTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = { AppBar(this, true, { finish() }, scrollBehavior = scrollBehavior) }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val bundle = intent.extras!!
                        val article = Article(
                            bundle.getString("author", ""),
                            bundle.getString("title", ""),
                            bundle.getString("description"),
                            bundle.getString("url", ""),
                            bundle.getString("urlToImage"),
                            bundle.getString("publishedAt", ""),
                            bundle.getString("content"),
                            bundle.getString("pathToImage"))

                        ArticleScreen(this, article, Modifier)
                    }
                }
            }
        }
    }
}