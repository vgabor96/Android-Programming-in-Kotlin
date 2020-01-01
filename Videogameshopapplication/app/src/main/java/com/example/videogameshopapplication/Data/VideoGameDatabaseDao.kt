package com.example.videogameshopapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoGameDatabaseDao {

    @Insert
    fun insert(videoGame: VideoGame)

    @Query("SELECT * FROM video_games_table ORDER BY id ASC")
    fun getAllVideoGames():LiveData<List<VideoGame>>

    @Delete
    fun delete(videoGame: VideoGame)

}