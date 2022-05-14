package com.moviesapi.domain.repository

import com.moviesapi.NetworkResult
import com.moviesapi.data.model.dto.PaginatedMoviesList
import com.moviesapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {


    //suspend fun getMoreMovies(query: String, page: String) : NetworkResult<List<Movie>>
    suspend fun getMoreMoviesPaginated(query: String, page: String): NetworkResult<PaginatedMoviesList>

    suspend fun storeMovies(movies : List<Movie>) : Unit
    fun getMoviesFlow() : Flow<List<Movie>>

    /* Get cached data.
    Store cached data.
    Get remote data.*/
}