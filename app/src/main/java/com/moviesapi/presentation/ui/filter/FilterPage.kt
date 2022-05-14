package com.moviesapi.presentation.ui.filter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moviesapi.presentation.ui.common.MovieItem
import com.moviesapi.presentation.ui.common.MySpinner


@Composable
fun FilterPage(vm: FilterViewModel = hiltViewModel()) {


    var movies = vm.movies.value
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {

        MySpinner(
            selectedType = vm.selectedType,
            OnFilterChangedEvent = { vm.onEvent(FilterPageEvent.OnFilterChangedEvent) })
        LazyColumn(modifier = Modifier.padding(top = 10.dp, bottom = 56.dp))
        {
            itemsIndexed(movies)
            { index, movie ->
                vm.onEvent(FilterPageEvent.UpdateCurrentScrollIndex(index))
                Divider()
                MovieItem(movie = movie, index = index)
            }
        }
    }

}