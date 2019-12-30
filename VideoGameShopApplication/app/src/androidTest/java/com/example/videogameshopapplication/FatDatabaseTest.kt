package com.example.videogameshopapplication

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.videogameshopapplication.data.DailyIntake
import com.example.videogameshopapplication.data.FatDatabase
import com.example.videogameshopapplication.data.FatDatabaseDao
import com.example.videogameshopapplication.data.FatFact
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class FatDatabaseTest {

    private lateinit var fatDao: FatDatabaseDao
    private lateinit var db: FatDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FatDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        fatDao = db.fatDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetDailyIntake() {
        val dailyIntake = DailyIntake(
            dailyOmega6 = 6f,
            dailyOmega3 = 3f,
            dailyCholesterol = 1f
        )
        fatDao.insert(dailyIntake)
        val lastIntake = fatDao.getLastIntake()

        assertNotNull(lastIntake)
        assertEquals(3f, lastIntake?.dailyOmega3)
    }

    @Test
    @Throws(Exception::class)
    fun getAllFatFacts() {
        val allFatFacts = fatDao.getAllFatFacts()

        assertNotNull(allFatFacts)
    }
}