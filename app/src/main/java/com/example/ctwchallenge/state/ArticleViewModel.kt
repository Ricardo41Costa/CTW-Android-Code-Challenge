package com.example.ctwchallenge.state

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctwchallenge.api.ApiClient
import com.example.ctwchallenge.data.Article
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.io.File
import java.io.IOException
import java.net.URL
import java.util.Date

sealed interface UiState {
    data class Success(val articles: ArrayList<Article>) : UiState
    object Error : UiState
    object Loading : UiState
}

class ArticleViewModel : ViewModel() {
    // This can be state flow
    var uiState: UiState by mutableStateOf(UiState.Loading)
        internal set

    init {
        //getArticles(token, source)
    }

    /*companion object {
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
    }*/

    fun getArticles(context: Context, token: String, source: String) {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {

                val call = ApiClient.apiManager.getTopHeadlines(token, source)
                val response = call.awaitResponse()
                val responseBody = response.body()
                val articles = responseBody!!.articles

                /*for (article in articles) {
                    if (article.urlToImage != null) {
                        try {
                            val url = URL(article.urlToImage)
                            val imageData = url.readBytes()
                            val file = File(context.cacheDir, Date().toString() + ".png")
                            file.createNewFile()
                            file.writeBytes(imageData)
                            article.pathToImage = file.absolutePath
                        } catch (e : Exception) {
                            Log.wtf("getArticles", e.toString())
                        }
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
