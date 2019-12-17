package com.example.videogameshopapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface VideoGameDatabaseDao {

    @Insert
    fun insert(videoGame: VideoGame)

    @Update
    fun update(videoGame: VideoGame)

    @Delete
    fun delete(videoGame: VideoGame)

    @Query("SELECT * FROM videogames_table ORDER BY name DESC")
    fun getAllVideoGames(): LiveData<List<VideoGame>>

    @Query("SELECT * FROM videogames_table ORDER BY name DESC LIMIT 1")
    fun getLastVideoGame(): VideoGame?

}