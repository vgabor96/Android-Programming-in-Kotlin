package com.example.videogameshopapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FatDatabaseDao {
    @Insert
    fun insert(dailyIntake: DailyIntake)

    @Insert
    fun insertFatFact(fatFact: FatFact)

    @Update
    fun update(dailyIntake: DailyIntake)

    @Query("SELECT * FROM daily_intakes_table ORDER BY date_of_intake DESC LIMIT 1")
    fun getLastIntake(): DailyIntake?

    @Query("SELECT * FROM daily_intakes_table ORDER BY date_of_intake DESC")
    fun getAllIntakes(): LiveData<List<DailyIntake>>

    @Query("SELECT * from fat_facts_table ORDER BY name DESC")
    fun getAllFatFacts(): LiveData<List<FatFact>>
}