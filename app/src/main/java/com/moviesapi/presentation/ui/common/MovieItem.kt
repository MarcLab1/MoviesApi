package com.moviesapi.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.moviesapi.R
import com.moviesapi.domain.model.Movie


@Composable
fun MovieItem(movie: Movie, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = AbsoluteCutCornerShape(0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier
                    .fillMaxWidth(.80f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    //Text(index.toString())
                    Image(
                        painter = rememberImagePainter(data = if (movie.primaryImage.isNullOrEmpty())
                            R.drawable.placeholder else movie.primaryImage,
                            builder = {
                                placeholder(R.drawable.placeholder)
                            }),

                        contentDescription = movie.titleText,
                        modifier = Modifier
                            .size(75.dp)
                            .padding(5.dp)
                    )

                    Column() {
                        Text(movie.titleText, style = MaterialTheme.typography.body1)
                        Text(movie.titleType, style = MaterialTheme.typography.subtitle2)
                    }

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.End
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        movie.releaseYear,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
