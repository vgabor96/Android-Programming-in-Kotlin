package com.example.videogameshopapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoGameDatabaseDao {

    @Insert
    fun insertVideoGame(videoGame: VideoGame)

    @Query("SELECT * FROM video_games_table ORDER BY name DESC")
    fun getAllVideoGames():LiveData<List<VideoGame>>


}