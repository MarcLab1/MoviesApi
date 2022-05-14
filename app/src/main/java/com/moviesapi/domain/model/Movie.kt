package com.moviesapi.domain.model

import com.moviesapi.data.model.entity.MovieEntity


data class Movie(
    val id: String,
    val primaryImage: String,
    val titleType: String,
    val titleText: String,
    val releaseYear: String,
)

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        primaryImage = primaryImage,
        titleType = titleType,
        titleText = titleText,
        releaseYear = releaseYear
    )
}
