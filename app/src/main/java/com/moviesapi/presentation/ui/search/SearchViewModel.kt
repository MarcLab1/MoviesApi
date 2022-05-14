package com.moviesapi.presentation.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviesapi.Constants
import com.moviesapi.NetworkResult
import com.moviesapi.domain.model.Movie
import com.moviesapi.domain.usecases.GetMoreMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMoreMoviesUseCase: GetMoreMoviesUseCase
) : ViewModel() {

    var movies: MutableState<List<Movie>> = mutableStateOf(emptyList())
    var query: MutableState<String> = mutableStateOf("")
    var currentPage: MutableState<Int> = mutableStateOf(Constants.FIRST_PAGE)
    var currentScrollPosition : MutableState<Int> = mutableStateOf(0)
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    var isLastPage: MutableState<Boolean> = mutableStateOf(false)

    private var queryJob: Job? = null

    fun onEvent(event : SearchPageEvent)
    {
        when(event)
        {
            is SearchPageEvent.UpdateQuery ->
            {
                updateQuery(event.newQuery)
            }
            is SearchPageEvent.NewSearchEvent ->
            {
                getMovies()
            }
            is SearchPageEvent.MoreSearchEvent ->
            {
                getMoreMovies()
            }
            is SearchPageEvent.UpdateCurrentScrollIndex ->
            {
                updateCurrentScrollPosition(event.newPosition)
            }
        }
    }

    fun updateQuery(newQuery: String) {
        queryJob?.cancel()
        queryJob = viewModelScope.launch {
            delay(1000) //delay while the user is typing
            query.value = newQuery
            getMovies()
        }
    }


    fun getMovies() {
        resetPagination()
        isLoading.value = true
        viewModelScope.launch {
            val paginatedMoviesResponse = getMoreMoviesUseCase.invoke(query.value, page = currentPage.value.toString())
            when(paginatedMoviesResponse)
            {
                is NetworkResult.Success -> { movies.value = paginatedMoviesResponse.data.movies
                    isLastPage.value = paginatedMoviesResponse.data.isLastPage
                }
                is NetworkResult.Error -> { movies.value = emptyList()}
                is NetworkResult.Exception -> { movies.value = emptyList()}
            }
            isLoading.value = false
        }
    }

    fun getMoreMovies() {
        if(isLastPage.value) return

        currentPage.value++
        isLoading.value = true

        viewModelScope.launch {
            val paginatedMoviesResponse = getMoreMoviesUseCase.invoke(query.value, page = currentPage.value.toString())
            when(paginatedMoviesResponse)
            {
                is NetworkResult.Success -> { movies.value += paginatedMoviesResponse.data.movies
                    isLastPage.value = paginatedMoviesResponse.data.isLastPage
                }
                is NetworkResult.Error -> { movies.value = emptyList()}
                is NetworkResult.Exception -> { movies.value = emptyList()}
            }
            isLoading.value = false
        }
    }

    fun updateCurrentScrollPosition(newPosition: Int) {
        currentScrollPosition.value = newPosition
    }

    fun resetPagination()
    {
        currentPage.value = Constants.FIRST_PAGE
        currentScrollPosition.value = 0
        isLastPage.value = false
    }
}