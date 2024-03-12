package com.example.ctwchallenge.screen

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ctwchallenge.R
import com.example.ctwchallenge.data.Article
import com.example.ctwchallenge.state.ArticleViewModel
import com.example.ctwchallenge.ui.theme.CTWChallengeTheme

@Composable
fun ArticleScreen(context: Context, article: Article, modifier: Modifier) {
    val image: Painter = painterResource(R.drawable.bbc_placeholder)
    if (article.pathToImage == null) {
        if (context.packageName.contains("bbc")) {
            painterResource(R.drawable.bbc_placeholder)
        } else {
            painterResource(R.drawable.abc_placeholder)
        }
    } else {
        BitmapPainter(BitmapFactory.decodeFile(article.pathToImage).asImageBitmap())
    }
    Column {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(
            text = article.title,
            fontSize = 24.sp,
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )

        if (article.description != null) {
            Text(
                text = article.description,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
        }

        if (article.content != null) {
            Text(
                text = article.content,
                fontSize = 18.sp,
                modifier = modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    CTWChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val article = Article("Ricardo", "Teste Noticia 1", "Teste de uma noticia para testar",
                "www.google.pt", null, "2024-03-10T10:37:15.2780736Z", "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n" +
                        "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.", null)
            ArticleScreen(article, Modifier)
        }
    }
}*/