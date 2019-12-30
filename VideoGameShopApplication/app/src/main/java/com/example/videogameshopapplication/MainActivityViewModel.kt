package com.example.videogameshopapplication

import android.app.Application
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.*
import com.example.videogameshopapplication.data.*
import com.example.videogameshopapplication.data.DailyIntake
import com.example.videogameshopapplication.data.VideoGameShopApplicationRepository
import com.example.videogameshopapplication.data.FatDatabase
import com.example.videogameshopapplication.data.FatFact
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class MainActivityViewModel(application: Application) : AndroidViewModel(application), AdapterView.OnItemSelectedListener {
    private val repository : VideoGameShopApplicationRepository
    private var dailyIntake = MutableLiveData<DailyIntake?>()
    var fatFacts:LiveData<List<FatFact>>
    val dailyIntakesString : LiveData<Spanned>
    lateinit var selectedFatFact : FatFact
    private var fatAmount =0f

    init {
        val dataSource = FatDatabase.getInstance(application).fatDatabaseDao
        repository= VideoGameShopApplicationRepository(dataSource)

        fatFacts=repository.fatFacts

        val dailyIntakes=repository.dailyIntakes
        dailyIntakesString = Transformations.map(dailyIntakes) { items ->
            formatDailyIntakes(items, application.resources)
        }

        initializeDailyIntake()
    }
    private fun initializeDailyIntake() {
        viewModelScope.launch {
            dailyIntake.value = repository.getTodayIntake()
        }
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        selectedFatFact=parent.getItemAtPosition(pos) as FatFact
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }


    fun onAdd(){
        val fat=selectedFatFact

        val newIntake = DailyIntake(
            dailyOmega3 = fat.omega3 * fatAmount,
            dailyOmega6 = fat.omega6 * fatAmount,
            dailyCholesterol = fat.cholesterol * fatAmount
        )

        viewModelScope.launch {
            repository.insert(newIntake)
        }
    }

    fun onTextChanged(text : CharSequence){
        try {
            fatAmount = text.toString().toFloat()
        } catch (e: NumberFormatException) {
            fatAmount=0f
        }
    }
}