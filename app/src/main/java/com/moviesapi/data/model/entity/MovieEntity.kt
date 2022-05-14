package com.moviesapi.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviesapi.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val primaryImage: String,
    val titleType: String,
    val titleText: String,
    val releaseYear: String
)

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        primaryImage = primaryImage,
        titleType = titleType,
        titleText = titleText,
        releaseYear = releaseYear
    )
}