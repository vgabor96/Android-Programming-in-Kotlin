package com.example.videogameshopapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoGameDatabaseDao {

    @Insert
    fun insert(videoGame: VideoGame)

    @Query("SELECT * FROM video_games_table ORDER BY id ASC")
    fun getAllVideoGames():LiveData<List<VideoGame>>

    @Delete
    fun delete(videoGame: VideoGame)

    @Query("SELECT * FROM video_games_table WHERE id = :id")
    fun findVideoGameById(id : Long):VideoGame

    @Query("UPDATE video_games_table SET name = :name, publisher = :publisher, platform= :platform, price = :price WHERE id = :id ")
    fun updateVideoGame(id : Long, name : String, publisher : String, platform : String, price : Float)

}