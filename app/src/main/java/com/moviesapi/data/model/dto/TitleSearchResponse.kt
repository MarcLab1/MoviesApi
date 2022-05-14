package com.moviesapi.data.model.dto

import com.moviesapi.domain.model.Movie

data class TitleSearchResponse(
    val page: Int?,
    val next: String?,
    val entries: Int?,
    val results: List<Result>?
)


fun TitleSearchResponse.toMovieList(): List<Movie> {

    return results?.map {
        Movie(
            it.id,
            it.primaryImage?.url ?: "",
            it.titleType?.text ?: "",
            it.titleText?.text ?: "",
            if (it.releaseYear?.year == null) "?" else it.releaseYear.year.toString()
        )
    } ?: emptyList()

}

fun TitleSearchResponse.toPaginatedMovieList(): PaginatedMoviesList {
    return PaginatedMoviesList(
        movies = results?.map {
            Movie(
                it.id,
                it.primaryImage?.url ?: "",
                it.titleType?.text ?: "",
                it.titleText?.text ?: "",
                if (it.releaseYear?.year == null) "?" else it.releaseYear.year.toString()
            )
        }
            ?: emptyList(),
        isLastPage = this.next.isNullOrEmpty())
}