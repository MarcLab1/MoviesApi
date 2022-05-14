package com.moviesapi.domain.usecases

import com.moviesapi.domain.repository.MovieRepository
import com.moviesapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilterMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    fun invoke(): Flow<List<Movie>> {
        return movieRepository.getMoviesFlow()
    }
}