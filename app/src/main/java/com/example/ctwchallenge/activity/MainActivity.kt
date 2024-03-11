@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ctwchallenge.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
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
import com.example.ctwchallenge.widgets.ArticlesScreen
import com.example.ctwchallenge.widgets.ErrorScreen
import com.example.ctwchallenge.widgets.LoadingScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CTWChallengeTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = { AppBar(scrollBehavior = scrollBehavior) }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val viewModel: ArticleViewModel by viewModels { ArticleViewModel.Factory }

                        when(viewModel.uiState) {
                            is UiState.Loading -> LoadingScreen(Modifier)
                            is UiState.Success -> ArticlesScreen((viewModel.uiState as UiState.Success).articles, Modifier)
                            else -> ErrorScreen(Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(red = 127, green = 0, blue = 30),
            titleContentColor = Color(red = 127, green = 0, blue = 30),
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
            )
        },
        modifier = modifier
    )
}