package com.example.ctwchallenge.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.ctwchallenge.R
import com.example.ctwchallenge.activity.DetailActivity
import com.example.ctwchallenge.data.Article
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getDateTime(iso: String): String {
    return try {
        val epoch: Long = Instant.parse(iso).toEpochMilli()
        val date = Date(epoch)
        SimpleDateFormat("dd/MM/yyyy HH:mm").format(date)
    } catch (e: Exception) {
        e.toString()
    }
}

@Composable
fun HomeScreen(context : Context, articles : List<Article>, modifier: Modifier) {
    LazyColumn {
        items(articles) { article ->
            val usePlaceholder: Boolean = article.urlToImage == null

            Surface(
                onClick = {
                    val bundle = Bundle()
                    bundle.putString("author", article.author)
                    bundle.putString("title", article.title)
                    bundle.putString("description", article.description)
                    bundle.putString("url", article.url)
                    bundle.putString("urlToImage", article.urlToImage)
                    bundle.putString("publishedAt", article.publishedAt)
                    bundle.putString("content", article.content)

                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtras(bundle)

                    startActivity(context, intent, null)
                },
                modifier = modifier
                    .height(200.dp)
            ) {
                Box(modifier.padding(8.dp), contentAlignment = Alignment.BottomStart) {
                    if (usePlaceholder) {
                        Image(
                            painter = if (context.packageName.contains("bbc")) painterResource(R.drawable.bbc_placeholder) else painterResource(R.drawable.abc_placeholder),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = modifier
                                .fillMaxWidth()
                        )
                    } else {
                        AsyncImage(
                            model = article.urlToImage,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = modifier
                                .fillMaxWidth()
                        )
                    }
                    Column(modifier.padding(8.dp)) {
                        Text(
                            text = article.title,
                            fontSize = 20.sp,
                            maxLines = 1,
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 20.sp,
                                shadow = Shadow(
                                    color = Color.Black, blurRadius = 20f
                                )
                            )
                        )
                        Text(
                            text = getDateTime(article.publishedAt),
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 16.sp,
                                shadow = Shadow(
                                    color = Color.Black, blurRadius = 20f
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}