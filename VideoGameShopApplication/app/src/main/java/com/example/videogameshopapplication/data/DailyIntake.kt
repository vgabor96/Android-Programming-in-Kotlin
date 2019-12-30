package com.example.videogameshopapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily_intakes_table")
data class DailyIntake (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,

    @ColumnInfo(name = "date_of_intake")
    val dateOfIntake: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "daily_omega_3")
    var dailyOmega3: Float = 0f,

    @ColumnInfo(name = "daily_omega_6")
    var dailyOmega6: Float = 0f,

    @ColumnInfo(name = "daily_cholesterol")
    var dailyCholesterol: Float = 0f
)