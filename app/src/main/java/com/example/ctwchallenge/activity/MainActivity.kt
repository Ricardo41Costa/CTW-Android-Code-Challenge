@file:OptIn( ExperimentalMaterial3Api::class)

package com.example.ctwchallenge.activity

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.core.content.ContextCompat
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

        val viewModel = ArticleViewModel()

        val source = if (packageName.contains("bbc")) {
            getString(R.string.bbc_source)
        } else {
            getString(R.string.abc_source)
        }

        val biometricManager = BiometricManager.from(this)
        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
            val executor = ContextCompat.getMainExecutor(this)
            val dialog = BiometricPrompt.Builder(this)
                .setTitle("Biometric Authentication")
                .setDescription("Please authenticate with your biometrics to continue")
                .setNegativeButton("Skip", executor) { dialog, _ ->
                    viewModel.getArticles(this, getString(R.string.api_token), source)
                }
                .build()

            dialog.authenticate(CancellationSignal(), executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    viewModel.getArticles(this@MainActivity, getString(R.string.api_token), source)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    viewModel.uiState = UiState.Error
                }
            })
        } else {
            viewModel.getArticles(this, getString(R.string.api_token), source)
        }

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