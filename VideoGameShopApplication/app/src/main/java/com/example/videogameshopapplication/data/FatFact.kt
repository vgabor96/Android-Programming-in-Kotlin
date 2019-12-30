package com.example.videogameshopapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "fat_facts_table")
data class FatFact (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name : String="",

    @ColumnInfo(name = "omega_3")
    var omega3: Float =0f,

    @ColumnInfo(name = "omega_6")
    var omega6: Float =0f,

    @ColumnInfo(name = "cholesterol")
    var cholesterol: Float =0f){

    override fun toString(): String {
        return name
    }
}