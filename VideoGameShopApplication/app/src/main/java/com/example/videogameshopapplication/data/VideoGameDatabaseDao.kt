package com.example.videogameshopapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FatDatabaseDao {

    @Insert
    fun AddVideoGame(videoGame: VideoGame)

    @Update
    fun UpdateVideoGame(videoGame: VideoGame)

    @Delete
    fun DeleteVideoGame(videoGame: VideoGame)

    @Query("SELECT * FROM videogames_table ORDER BY name DESC")
    fun getAllVideoGames(): LiveData<List<VideoGame>>

}