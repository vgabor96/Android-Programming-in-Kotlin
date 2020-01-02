package com.example.videogameshopapplication

import android.app.Application
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
    var repository: VideoGameRepository
    //var videoGames:LiveData<List<VideoGame>>
    var videoGameList:ArrayList<VideoGame> = ArrayList<VideoGame>()
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



        repository.videoGames.value?.forEach {
            videoGameList.add(it)
        }

        videoGameString = Transformations.map( repository.videoGames) { items ->
            formatVideoGames(items, application.resources)

        }


    }

fun Update_VideoGameList() {


        videoGameList.clear()

    repository.videoGames.value?.forEach {
            videoGameList.add(it)
        }


}

    fun onIdColClicked(idbtnasc : Boolean){
        if(idbtnasc){
             videoGameList.sortWith(compareBy({it.id}))

        }else {

            videoGameList.sortWith(compareBy({it.id}))
            videoGameList.reverse()
        }

    }

    fun onNameColClicked(idbtnasc : Boolean){
        if(idbtnasc){
            videoGameList.sortWith(compareBy({it.name}))

        }else {

            videoGameList.sortWith(compareBy({it.name}))
            videoGameList.reverse()
        }
    }

    fun onPublisherColClicked(idbtnasc : Boolean){
        if(idbtnasc){

            videoGameList.sortWith(compareBy({it.publisher}))

        }else {

            videoGameList.sortWith(compareBy({it.publisher}))
            videoGameList.reverse()
        }
    }

    fun onPlatformColClicked(idbtnasc : Boolean){
        if(idbtnasc){

            videoGameList.sortWith(compareBy({it.platform}))

        }else {

            videoGameList.sortWith(compareBy({it.platform}))
            videoGameList.reverse()
        }
    }

    fun onPriceColClicked(idbtnasc : Boolean){
        if(idbtnasc){

            videoGameList.sortWith(compareBy({it.price}))

        }else {

            videoGameList.sortWith(compareBy({it.price}))
            videoGameList.reverse()
        }
    }


    fun onAddorModify(){

        var id : Long = selectedVideoGameID
        var name : String = selectedVideoGameName
        var publisher : String = selectedVideoGamePublisher
        var platform : String = selectedVideoGamePlatfrom
        var price : Float = selectedVideoGamePrice

        viewModelScope.launch {
            if (repository.findVideoGameById(id) != null) {
              onModify(id,name,publisher,platform,price)
            }
            else{
                onAdd(name,publisher,platform,price)
            }

        }

    }

    fun onAdd(name : String, publisher : String, platform : String, price : Float) {


            viewModelScope.launch {

                repository.insert( VideoGame(name = name, publisher = publisher, platform = platform, price=price))
                //Update_VideoGameList()
                videoGameList?.sortedWith(compareByDescending({it.id}))
            }

    }

    fun onModify(id: Long,name : String, publisher : String, platform : String, price : Float) {


        viewModelScope.launch {

            repository.updateVideoGame(id,name,publisher,platform,price)
            //Update_VideoGameList()
            videoGameList?.sortedWith(compareByDescending({it.id}))
        }

    }

    fun onDelete(id : Long) {
        viewModelScope.launch {
            if (repository.findVideoGameById(id) != null) {
                repository.delete(repository.findVideoGameById(id))
              //  Update_VideoGameList()
                videoGameList?.sortedWith(compareByDescending({it.id}))
            }
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
    fun onTextChangedPrice(text : CharSequence){
        try {
            selectedVideoGamePrice = text.toString().toFloat()
        }catch (e: NumberFormatException) {
            selectedVideoGamePrice = 0f
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedVideoGamePlatfrom=parent?.getItemAtPosition(position) as String

        Log.i("MainActivityViewModel", "selected: "+selectedVideoGamePlatfrom)

    }

}