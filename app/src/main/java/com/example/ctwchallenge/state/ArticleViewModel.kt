package com.example.ctwchallenge.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ctwchallenge.R
import com.example.ctwchallenge.api.ApiClient
import com.example.ctwchallenge.data.Article
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.IOException
sealed interface UiState {
    data class Success(val articles: ArrayList<Article>) : UiState
    object Error : UiState
    object Loading : UiState
}

class ArticleViewModel(token: String, source: String) : ViewModel() {
    // This can be state flow
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getArticles(token, source)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                val source = if (application.packageName.contains("bbc")) {
                    application.getString(R.string.bbc_source)
                } else {
                    application.getString(R.string.abc_source)
                }

                return ArticleViewModel(
                    application.getString(R.string.api_token),
                    source
                ) as T
            }
        }
    }


    private fun getArticles(token: String, source: String) {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                val call = ApiClient.apiManager.getTopHeadlines(token, source)
                val response = call.awaitResponse()
                val responseBody = response.body()
                val articles = responseBody!!.articles

                /*for (article in articles) {
                    if (article.urlToImage != null) {
                        val url = URL("https://www.example.com/image.png")
                        val imageData = url.readBytes()

                    }
                }*/

                UiState.Success(
                    articles
                )
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }
}
