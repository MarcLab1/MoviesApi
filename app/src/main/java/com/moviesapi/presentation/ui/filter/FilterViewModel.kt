package com.moviesapi.presentation.ui.filter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviesapi.Constants
import com.moviesapi.domain.model.Movie
import com.moviesapi.domain.usecases.GetFilterMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getFilterMoviesUseCase: GetFilterMoviesUseCase

) : ViewModel() {

    var movies: MutableState<List<Movie>> = mutableStateOf(emptyList())
    var selectedType: MutableState<String> = mutableStateOf(Constants.movietypes[0])
    var currentScrollPosition: MutableState<Int> = mutableStateOf(0)

    init {
        onEvent(FilterPageEvent.OnFilterChangedEvent)
    }

    fun onEvent(event: FilterPageEvent) {
        when (event) {
            is FilterPageEvent.OnFilterChangedEvent -> {
                viewModelScope.launch {
                    getFilterMoviesUseCase.invoke().collect {
                        movies.value = it.filter {
                            it.titleType == selectedType.value
                        }
                    }
                }
            }
            is FilterPageEvent.UpdateCurrentScrollIndex -> {
                updateCurrentScrollPosition(event.newPosition)
            }
        }
    }

    fun updateCurrentScrollPosition(newPosition: Int) {
        currentScrollPosition.value = newPosition
    }

}