package com.example.videogameshopapplication.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="video_games_table")
data class VideoGame (

    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,

    @ColumnInfo(name="name")
    var name:String="",

    @ColumnInfo(name="publisher")
    var publisher:String="",

    @ColumnInfo(name="platform")
    var platform:String="",

    @ColumnInfo(name="price")
    var price:Float=0f) {

    override fun toString(): String {
        return "${id}\t${name}\t${publisher}\t${platform}\t${price}"
    }


}