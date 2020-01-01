package com.example.videogameshopapplication.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities=[VideoGame::class], version=1, exportSchema=false)
abstract class VideoGameDatabase : RoomDatabase() {
    abstract val videoGameDatabaseDao: VideoGameDatabaseDao
    companion object {

        private var INSTANCE: VideoGameDatabase? = null

        fun getInstance(context: Context): VideoGameDatabase {
            synchronized(this) {
                var instance = INSTANCE

            if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideoGameDatabase::class.java,
                        "video_game_database"
                    )
                        .addCallback(seedDatabaseCallback(context))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }

        private fun seedDatabaseCallback(context: Context): RoomDatabase.Callback {
                return object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable {
                            var videoGameDao= getInstance(context).videoGameDatabaseDao
                            for(vg in getVideoGames())
                                videoGameDao.insert(vg)
                        }).start()
                    }
                }
        }

    }


}