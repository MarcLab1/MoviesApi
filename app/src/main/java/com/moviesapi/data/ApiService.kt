package com.moviesapi.data

import com.moviesapi.Constants
import com.moviesapi.data.model.dto.TitleSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers(Constants.HEADER1, Constants.HEADER2)
    @GET("title/{query}")
    suspend fun getMoreTitles(
        @Path("query") query: String,
        @Query("page") page: String
    ): TitleSearchResponse
}
