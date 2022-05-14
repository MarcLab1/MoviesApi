package com.moviesapi.presentation.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.moviesapi.domain.model.Movie
import com.moviesapi.presentation.theme.MoviesApiTheme
import com.moviesapi.presentation.ui.common.MovieItem

@Composable
fun SearchPage(vm: SearchViewModel = hiltViewModel()) {
    var movies = vm.movies.value
    Column() {
        SearchTextField(
            query = vm.query,
            onQueryChange = { vm.onEvent(SearchPageEvent.UpdateQuery(it)) })
        LazyColumn(modifier = Modifier.padding(bottom = 56.dp))
        {
            itemsIndexed(movies)
            { index, movie ->
                vm.onEvent(SearchPageEvent.UpdateCurrentScrollIndex(index))
                Divider()
                MovieItem(movie = movie, index = index)
                if (((index + 1) >= movies.size && !vm.isLoading.value))
                    vm.onEvent(SearchPageEvent.MoreSearchEvent)
            }
        }
    }
}


@Composable
fun SearchTextField(query: MutableState<String>, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query.component1(),
        onValueChange = {
            query.value = it
            onQueryChange(query.value)
        },
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Preview(
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun MovieItemPreview() {

    MoviesApiTheme() {
        Surface(color = MaterialTheme.colors.background) {
            MovieItem(
                movie = Movie(
                    id = "6734737",
                    primaryImage = "https://m.media-amazon.com/images/M/MV5BZTc5MWEwODYtYzAxOS00YmM4LThhYmUtNjFiMWE1MjU3MjhhXkEyXkFqcGdeQXVyNjIyMzMxMTk@._V1_.jpg",
                    titleType = "kljsdflkj",
                    titleText = "lkjsdflkjfds",
                    releaseYear = "1999"
                ),
                index = 22
            )
        }
    }
}


