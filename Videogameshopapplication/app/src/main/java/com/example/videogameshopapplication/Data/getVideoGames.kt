package com.example.videogameshopapplication.Data

fun getVideoGames()=listOf<VideoGame>(
    VideoGame(name = "Devil May Cry", publisher = "Capcom", platform = "PS2", price=10f),
    VideoGame(name = "Devil May Cry2", publisher = "Capcom", platform = "PS2", price=10f),
    VideoGame(name = "Devil May Cry3", publisher = "Capcom", platform = "PS2", price=10f),
    VideoGame(name = "Devil May Cry4", publisher = "Capcom", platform = "PS3", price=10f),
    VideoGame(name = "Devil May Cry5", publisher = "Capcom", platform = "PS4", price=10f)
)

fun getAllPlatforms()= listOf<String>(
    "PS2",
    "PS3",
    "PS4",
    "XBOX360",
    "XBOXONE",
    "PC"

)