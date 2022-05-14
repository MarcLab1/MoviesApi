package com.moviesapi.data.di

import android.app.Application
import androidx.room.Room
import com.moviesapi.Constants
import com.moviesapi.data.ApiService
import com.moviesapi.data.database.MovieDatabase
import com.moviesapi.domain.repository.MovieRepository
import com.moviesapi.data.repository.ProdMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideString() = "hello world"

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(
            Constants.BASE_URL
        ).build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService, movieDatabase: MovieDatabase): MovieRepository {
        return ProdMovieRepository(apiService = apiService, movieDatabase = movieDatabase)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(application : Application) : MovieDatabase
    {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies")
            .fallbackToDestructiveMigration().build()
    }
}