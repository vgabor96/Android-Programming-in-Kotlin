package com.example.videogameshopapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class VideoGameRepository(private  val database: VideoGameDatabaseDao) {



    val videoGames=database.getAllVideoGames()

    suspend fun getVideoGame(): VideoGame? {
        return withContext(Dispatchers.IO) {
            var lastIntake = database.getLastVideoGame()
            var result =lastIntake

         result
        }
    }

    suspend fun insert(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.insert(videoGame)
        }
    }

    suspend fun update(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.update(videoGame)
        }
    }
}