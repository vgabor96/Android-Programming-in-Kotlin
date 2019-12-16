package com.example.videogameshopapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videogames_table")
data class VideoGame (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name= "name")
    var name: String="",

    @ColumnInfo(name ="publisher")
    var publisher: String="",

    @ColumnInfo(name="platform")
    var platform: String="",

    @ColumnInfo(name="price")
    var price: Float = 0f) {

    override fun toString():String {
        return this.name
    }
}