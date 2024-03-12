package com.example.ctwchallenge.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.ctwchallenge.screen.ErrorScreen
import com.example.ctwchallenge.screen.HomeScreen
import com.example.ctwchallenge.screen.LoadingScreen
import com.example.ctwchallenge.state.ArticleViewModel
import com.example.ctwchallenge.state.UiState
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTWChallengeTheme {
                Scaffold(
                    modifier = Modifier
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val bundle = intent.extras


                    }
                }
            }
        }
    }
}

