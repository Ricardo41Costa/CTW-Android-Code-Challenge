@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ctwchallenge.widgets

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.ctwchallenge.R

@Composable
fun AppBar(
    context : Context,
    canNavigateBack : Boolean,
    goBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier) {

    val title = if (context.packageName.contains("bbc")) {
        stringResource(R.string.bbc_title)
    } else {
        stringResource(R.string.abc_title)
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(red = 73, green = 93, blue = 146),
            titleContentColor = Color(red = 73, green = 93, blue = 146),
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                color = Color.White,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = goBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Go Back"
                    )
                }
            }
        }
    )
}