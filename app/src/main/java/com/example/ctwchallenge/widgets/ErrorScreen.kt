package com.example.ctwchallenge.widgets

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme

@Composable
fun ErrorScreen(modifier: Modifier) {

}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    CTWChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ErrorScreen(Modifier)
        }

    }
}