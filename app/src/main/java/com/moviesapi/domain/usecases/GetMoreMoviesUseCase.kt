package com.moviesapi.domain.usecases

import com.moviesapi.NetworkResult
import com.moviesapi.data.model.dto.PaginatedMoviesList
import com.moviesapi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoreMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun invoke(query: String, page: String): NetworkResult<PaginatedMoviesList> {
        val paginatedMoviesResponse = movieRepository.getMoreMoviesPaginated(query = query, page = page)
        if(paginatedMoviesResponse is NetworkResult.Success)
            movieRepository.storeMovies(paginatedMoviesResponse.data.movies)

        return paginatedMoviesResponse
    }
}