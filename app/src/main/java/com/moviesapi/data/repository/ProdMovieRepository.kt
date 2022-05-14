package com.moviesapi.data.repository

import com.moviesapi.NetworkResult
import com.moviesapi.data.ApiService
import com.moviesapi.data.database.MovieDatabase
import com.moviesapi.data.model.dto.PaginatedMoviesList
import com.moviesapi.data.model.dto.toMovieList
import com.moviesapi.data.model.dto.toPaginatedMovieList
import com.moviesapi.data.model.entity.toMovie
import com.moviesapi.domain.model.Movie
import com.moviesapi.domain.model.toMovieEntity
import com.moviesapi.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException

class ProdMovieRepository(
    private val apiService: ApiService,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    private val dao = movieDatabase.getDao()

   /* override suspend fun getMoreMovies(query: String, page: String): NetworkResult<List<Movie>> {

        try {
            val result = apiService.getMoreTitles(query = query, page = page).toMovieList()
            return NetworkResult.Success(data = result)
        } catch (ex: HttpException) {
            return NetworkResult.Error(code = ex.code(), message = ex.message())
        } catch (ex: Exception) {
            return NetworkResult.Exception(ex)
        }
    }*/

    override suspend fun getMoreMoviesPaginated(query: String, page: String): NetworkResult<PaginatedMoviesList> {
        try {
            val result = apiService.getMoreTitles(query = query, page = page).toPaginatedMovieList()
            return NetworkResult.Success(data = result)
        } catch (ex: HttpException) {
            return NetworkResult.Error(code = ex.code(), message = ex.message())
        } catch (ex: Exception) {
            return NetworkResult.Exception(ex)
        }
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        return dao.insertMovies(movies.map { it.toMovieEntity() })
    }

    override fun getMoviesFlow(): Flow<List<Movie>> {
        return dao.getMoviesFlow().map { it.map { it.toMovie() } }
    }
}