package com.example.videogameshopapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.videogameshopapplication.data.VideoGame
import com.example.videogameshopapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    */

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var  spinner: Spinner
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        initData()

        binding.editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.onTextChanged(s)
            }
        })
    }


    //https://medium.com/@wa.maebenson06/using-android-livedata-in-spinners-room-428de4238847
    private fun initData() {
        var adapter = ArrayAdapter<VideoGame>(this, android.R.layout.simple_spinner_item)

        viewModel.videoGames.observe(this, Observer { videoGames ->

            videoGames?.forEach {
                adapter.add(it)
            }
        })

        spinner=findViewById(R.id.spinner)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = viewModel

    }

}
