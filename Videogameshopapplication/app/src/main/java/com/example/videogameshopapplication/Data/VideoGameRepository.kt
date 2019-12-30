package com.example.videogameshopapplication.Data

class VideoGameRepository(private val database:VideoGameDatabaseDao) {

    val videoGames=database.getAllVideoGames()

}