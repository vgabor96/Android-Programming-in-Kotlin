package com.example.videogameshopapplication.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(VideoGame::class), version = 1,  exportSchema = false)
abstract class VideoGameDatabase : RoomDatabase() {

    abstract val videogameDatabaseDao: VideoGameDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: VideoGameDatabase? = null
        fun getInstance(context: Context): VideoGameDatabase {
            Log.i("VideoGameDatabase", "get instance entry")
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideoGameDatabase::class.java,
                        "videogame_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(seedDatabaseCallback(context))
                        .build()
                    Log.i("VideoGameDatabase", "build")
                    INSTANCE = instance
                }
                Log.i("VideoGameDatabase", "return instance")
                return instance
            }
        }

       private fun seedDatabaseCallback(context: Context): RoomDatabase.Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.i("VideoGameDatabase", "Seed onCreate")
                    Thread(Runnable {
                        var videogameDao= getInstance(context).videogameDatabaseDao
                        for (vg in getVideoGames()){
                            videogameDao.insert(vg)
                            Log.i("VideoGameDatabase", vg.name)
                        }
                    }).start()
                }
            }
        }
    }
}
