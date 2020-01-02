package com.example.videogameshopapplication.Data

import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.room.FtsOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoGameRepository(private val database:VideoGameDatabaseDao) {


    var videoGames=database.getAllVideoGames()

      fun orderByIdASC() {

            videoGames = database.getAllVideoGames()



     }

      fun orderByIdDESC(){

         videoGames = database.getAllVideoGamesOrderByIDDSC()


    }

    suspend fun orderByNameASC(){
        withContext(Dispatchers.IO) {
            database.getAllVideoGamesOrderByName()
        }
    }

    suspend fun orderByNameDESC(){
        withContext(Dispatchers.IO) {
            database.getAllVideoGamesOrderByNameDESC()
        }
    }

    suspend fun orderByPublisherASC(){
        withContext(Dispatchers.IO) {
           database.getAllVideoGamesOrderByPublisher()
        }
    }

    suspend fun orderByPublisherDESC(){
        withContext(Dispatchers.IO) {
           database.getAllVideoGamesOrderByPublisherDESC()
        }
    }

    suspend fun orderByPlatformASC(){
        withContext(Dispatchers.IO) {
           database.getAllVideoGamesOrderByPlatform()
        }
    }

    suspend fun orderByPlatformDESC(){
        withContext(Dispatchers.IO) {
            database.getAllVideoGamesOrderByPlatformDESC()
        }
    }

    suspend fun orderByPriceASC(){
        withContext(Dispatchers.IO) {
           database.getAllVideoGamesOrderByPrice()
        }
    }

    suspend fun orderByPriceDESC(){
        withContext(Dispatchers.IO) {
            database.getAllVideoGamesOrderByPriceDESC()
        }
    }


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