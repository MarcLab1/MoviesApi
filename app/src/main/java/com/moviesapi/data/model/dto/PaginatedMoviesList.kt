package com.moviesapi.data.model.dto

import com.moviesapi.domain.model.Movie

data class PaginatedMoviesList(val movies: List<Movie>, val isLastPage: Boolean )
