package com.example.videogameshopapplication.Data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameRepository(private val database:VideoGameDatabaseDao) {

    val videoGames=database.getAllVideoGames()

    suspend fun insert(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.insert(videoGame)
        }
    }

    suspend fun delete(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.delete(videoGame)
        }
    }

    suspend fun findVideoGameById(id : Long):VideoGame {
        return withContext(Dispatchers.IO) {
            database.findVideoGameById(id)
        }
    }

    suspend fun updateVideoGame(id : Long, name : String, publisher : String, platform : String, price : Float){
        return withContext(Dispatchers.IO) {
            database.updateVideoGame(id,name,publisher,platform,price)
        }
    }

}