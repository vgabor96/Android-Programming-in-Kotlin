package com.example.videogameshopapplication.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(DailyIntake::class, FatFact::class), version = 1,  exportSchema = false)
abstract class FatDatabase : RoomDatabase() {

    abstract val fatDatabaseDao: FatDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: FatDatabase? = null
        fun getInstance(context: Context): FatDatabase {
            Log.i("FatDatabase", "get instance entry")
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FatDatabase::class.java,
                        "fat_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(
                            seedDatabaseCallback(
                                context
                            )
                        )
                        .build()
                    Log.i("FatDatabase", "build")
                    INSTANCE = instance
                }
                Log.i("FatDatabase", "return instance")
                return instance
            }
        }

        //https://matthiaslischka.at/2019/01/15/Seed-Room-Database/
        //https://anadea.info/blog/how-to-pre-populate-android-room-database-on-first-application-launch
        private fun seedDatabaseCallback(context: Context): RoomDatabase.Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("FatDatabase", "Seed onCreate")
                    Thread(Runnable {
                        var fatDao= getInstance(
                            context
                        ).fatDatabaseDao
                        for (f in getFatFacts()){
                            fatDao.insertFatFact(f)
                            Log.i("FatDatabase", f.name)
                        }
                    }).start()
                }
            }
        }
    }
}
