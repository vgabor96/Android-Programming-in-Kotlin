package com.example.videogameshopapplication.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.time.days
import androidx.lifecycle.LiveData


class VideoGameShopApplicationRepository(private  val database: FatDatabaseDao) {

    val dailyIntakes=database.getAllIntakes()

    val fatFacts=database.getAllFatFacts()

//    suspend fun getAllFatFacts() : LiveData<List<FatFact>> {
//        return withContext(Dispatchers.IO)  {
//            val result =database.getAllFatFacts()
//            result
//        }
//    }
    suspend fun getTodayIntake(): DailyIntake? {
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

    suspend fun insert(dailyIntake: DailyIntake) {
        withContext(Dispatchers.IO) {
            database.insert(dailyIntake)
        }
    }

    suspend fun update(dailyIntake: DailyIntake) {
        withContext(Dispatchers.IO) {
            database.update(dailyIntake)
        }
    }
}