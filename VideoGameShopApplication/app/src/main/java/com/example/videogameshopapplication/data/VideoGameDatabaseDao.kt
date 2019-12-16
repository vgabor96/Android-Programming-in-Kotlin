package com.example.videogameshopapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoGameDatabaseDao {

    @Insert
    fun addVideoGame(videoGame: VideoGame)

    @Update
    fun updateVideoGame(videoGame: VideoGame)

    @Delete
    fun deleteVideoGame(videoGame: VideoGame)

    @Query("SELECT * FROM videogames_table ORDER BY name DESC")
    fun getAllVideoGames(): LiveData<List<VideoGame>>

}