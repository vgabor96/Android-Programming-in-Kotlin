package com.example.videogameshopapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class VideoGameRepository(private  val database: VideoGameDatabaseDao) {



    val videoGames=database.getAllVideoGames()

   /* suspend fun getTodayIntake(): DailyIntake? {
        return withContext(Dispatchers.IO) {
            var lastIntake = database.getLastIntake()
            var result =lastIntake

            lastIntake?.let{
                val date = Date(it.dateOfIntake)
                if (date != Date(System.currentTimeMillis())) {
                    result = null
                }
            }
            result
        }
    }
*/
    suspend fun Add(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.addVideoGame(videoGame)
        }
    }

    suspend fun update(videoGame: VideoGame) {
        withContext(Dispatchers.IO) {
            database.updateVideoGame(videoGame)
        }
    }
}