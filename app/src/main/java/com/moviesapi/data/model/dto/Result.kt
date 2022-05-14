package com.moviesapi.data.model.dto

data class Result(
    val id: String = "",
    val primaryImage: PrimaryImage?,
    val titleType: TitleType?,
    val titleText: TitleText?,
    val releaseYear: ReleaseYear?,
    val releaseDate: ReleaseDate?
)