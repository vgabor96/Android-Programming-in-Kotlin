package com.example.videogameshopapplication

import android.app.Application
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.videogameshopapplication.Data.VideoGame
import com.example.videogameshopapplication.Data.VideoGameDatabase
import com.example.videogameshopapplication.Data.VideoGameRepository
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
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
    val videoGameList:ArrayList<VideoGame> = ArrayList<VideoGame>()
    private var selectedVideoGameID: Long = -1
    private var selectedVideoGameName: String = ""
    private var selectedVideoGamePublisher: String = ""
    private var selectedVideoGamePlatfrom: String = ""
    private var selectedVideoGamePrice: Float = 0f
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

    fun onAdd()
    {
        var name : String = selectedVideoGameName
        var publisher : String = selectedVideoGamePublisher
        var platform : String = selectedVideoGamePlatfrom
        var price : Float = selectedVideoGamePrice

            viewModelScope.launch {

                repository.insert( VideoGame(name = name, publisher = publisher, platform = platform, price=price))
                videoGameList.clear()
            }

    }

    fun onDelete(videoGame: VideoGame)
    {
        viewModelScope.launch {

            repository.delete( videoGame)
            videoGameList.clear()
        }

    }

    fun onTextChangedID(text : CharSequence){
        try {
                selectedVideoGameID = text.toString().toLong()
        }catch (e: NumberFormatException){
            selectedVideoGameID = 0
        }
    }
    fun onTextChangedName(text : CharSequence){
        selectedVideoGameName = text.toString()
    }
    fun onTextChangedPublisher(text : CharSequence){
        selectedVideoGamePublisher = text.toString()
    }
    fun onTextChangedPlatform(text : CharSequence){
        selectedVideoGamePlatfrom = text.toString()
    }
    fun onTextChangedPrice(text : CharSequence){
        try {
            selectedVideoGamePrice = text.toString().toFloat()
        }catch (e: NumberFormatException) {
            selectedVideoGamePrice = 0f
        }

    }



}