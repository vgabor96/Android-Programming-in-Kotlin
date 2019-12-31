package com.example.videogameshopapplication

import android.app.Application
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.videogameshopapplication.Data.VideoGame
import com.example.videogameshopapplication.Data.VideoGameDatabase
import com.example.videogameshopapplication.Data.VideoGameRepository
import javax.sql.DataSource

class MainActivityViewModel(application: Application): AndroidViewModel(application), AdapterView.OnItemSelectedListener {

     val videoGameString: LiveData<Spanned>

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedPlatform=parent?.getItemAtPosition(position) as String

        Log.i("MainActivityViewModel", "selected: "+selectedPlatform)

        }

    private val repository: VideoGameRepository
    var videoGames:LiveData<List<VideoGame>>
    lateinit var selectedVideoGame: VideoGame
    lateinit var selectedPlatform: String

    init {
        val dataSource=VideoGameDatabase.getInstance(application).videoGameDatabaseDao
        repository= VideoGameRepository(dataSource)

        videoGames=repository.videoGames

        videoGameString = Transformations.map(videoGames) { items ->
            formatVideoGames(items, application.resources)

        }


    }
}