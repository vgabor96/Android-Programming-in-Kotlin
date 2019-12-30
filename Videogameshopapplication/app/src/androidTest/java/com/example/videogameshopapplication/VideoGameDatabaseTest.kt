package com.example.videogameshopapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.videogameshopapplication.Data.VideoGameDatabase
import com.example.videogameshopapplication.Data.VideoGameDatabaseDao
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VideoGameDatabaseTest {

    private lateinit var videoGameDao: VideoGameDatabaseDao
    private lateinit var db: VideoGameDatabase

    @Before
    fun createDb() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext


        db = Room.inMemoryDatabaseBuilder(context, VideoGameDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        videoGameDao = db.videoGameDatabaseDao

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}