package com.moviesapi.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.moviesapi.data.model.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun getDao() : MovieDao
}