package com.example.ctwchallenge.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme

@Composable
fun LoadingScreen(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = Modifier.width(32.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    CTWChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoadingScreen(Modifier)
        }

    }
}