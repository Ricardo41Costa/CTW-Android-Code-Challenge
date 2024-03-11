package com.example.ctwchallenge.widgets

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ctwchallenge.R
import com.example.ctwchallenge.data.Article
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme
import java.text.SimpleDateFormat
import java.util.Date

fun populateArticles(): ArrayList<Article> {
    val out: ArrayList<Article> = arrayListOf()

    out.add(Article("Ricardo", "Teste Noticia 1", "Teste de uma noticia para testar",
        "www.google.pt", null, "2024-03-10T10:37:15.2780736Z", null, null)
    )
    out.add(Article("Ricardo", "Teste Noticia 2", "Teste de outra noticia para testar e mostar coisas giras",
        "www.youtube.pt", null, "2024-03-10T10:37:15.2780736Z", null, null)
    )

    return out
}

@SuppressLint("SimpleDateFormat")
fun getDateTime(iso: String): String {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssssss'Z'")
        val date: Date? = format.parse(iso)
        SimpleDateFormat("dd/MM/yyyy HH:mm").format(date!!)
    } catch (e: Exception) {
        Log.wtf("TAG", e.toString());
        e.toString()
    }
}

@Composable
fun ArticlesScreen(articles : List<Article>, modifier: Modifier) {
    LazyColumn {
        items(articles) { article ->
            var image: Painter

            if (article.pathToImage == null) {
                image = painterResource(R.drawable.bbc_placeholder);
            } else {
                image = BitmapPainter(BitmapFactory.decodeFile(article.pathToImage).asImageBitmap())
            }

            Box(modifier.padding(8.dp), contentAlignment = Alignment.BottomStart) {
                Image(
                    painter = image,
                    contentDescription = null
                )
                Column(modifier.padding(8.dp)) {
                    Text(
                        text = article.title,
                        fontSize = 20.sp,
                        maxLines = 1,
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp,
                            shadow = Shadow(
                                color = Color.Black, blurRadius = 15f
                            )
                        )
                    )
                    Text(
                        text = getDateTime(article.publishedAt),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 12.sp,
                            shadow = Shadow(
                                color = Color.Black, blurRadius = 15f
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesScreenPreview() {
    CTWChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val articles: ArrayList<Article> = populateArticles()
            ArticlesScreen(articles, Modifier)
        }
    }
}