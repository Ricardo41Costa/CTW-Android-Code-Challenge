@file:OptIn( ExperimentalMaterial3Api::class)

package com.example.ctwchallenge.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.ctwchallenge.R
import com.example.ctwchallenge.state.ArticleViewModel
import com.example.ctwchallenge.state.UiState
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme
import com.example.ctwchallenge.screen.ErrorScreen
import com.example.ctwchallenge.screen.HomeScreen
import com.example.ctwchallenge.screen.LoadingScreen
import com.example.ctwchallenge.widgets.AppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTWChallengeTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = { AppBar(this, false, {}, scrollBehavior = scrollBehavior) }
                ) { innerPadding ->
                    Surface(
                            modifier = Modifier.padding(innerPadding),
                            color = MaterialTheme.colorScheme.background
                    ) {
                        val viewModel: ArticleViewModel by viewModels { ArticleViewModel.Factory }

                        when(viewModel.uiState) {
                            is UiState.Loading -> LoadingScreen(Modifier)
                            is UiState.Success -> HomeScreen(this, (viewModel.uiState as UiState.Success).articles, Modifier)
                            else -> ErrorScreen(Modifier)
                        }
                    }
                }
            }
        }
    }
}