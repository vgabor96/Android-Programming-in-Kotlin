package com.example.videogameshopapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.videogameshopapplication.Data.VideoGame
import com.example.videogameshopapplication.Data.getVideoGames
import com.example.videogameshopapplication.databinding.ActivityMainBinding
import android.widget.Toast
import android.widget.TextView
import android.R.attr.category
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.videogameshopapplication.Data.getAllPlatforms
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var spinner: Spinner
    private lateinit var tablerow: TextView
    private lateinit var listview: ListView
    private lateinit var llgames: LinearLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var textall: TextView
    private val productList = ArrayList<VideoGame>()
    private var isinit : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel= ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        initData()

        binding.idTxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
              viewModel.onTextChangedID(s)
            }
        })

        binding.nameTxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.onTextChangedName(s)
            }
        })

        binding.publisherTxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.onTextChangedPublisher(s)
            }
        })

        binding.platformTxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.onTextChangedPlatform(s)
            }
        })

        binding.priceTxt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.onTextChangedPrice(s)
            }
        })

    }



    private fun initData() {
      var adapter1=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item)

        listview = findViewById(R.id.listview)

            viewModel.videoGameList.clear()
        viewModel.videoGames.observe(this, Observer {
                videoGames -> videoGames?.forEach {

                    viewModel.videoGameList.add(it)
                }
             var adapter = listviewAdapter(this, viewModel.videoGameList)

            listview.setAdapter(adapter)
            adapter.notifyDataSetChanged()

        })

/*
            viewModel.videoGames.observe(this, Observer {
              videoGames ->
          run loop@{
              videoGames?.forEach {
                 if (!isinit){
                      return@loop
                   }
                     productList.add(it)
                 }

              }
             if (!isinit){
                   productList.add(videoGames.last())
              }
             isinit = false



                  var adapter = listviewAdapter(this, productList)

                 listview.setAdapter(adapter)
                 adapter.notifyDataSetChanged()

     })
*/




        listview.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val sid = (view.findViewById(R.id.tv_id) as TextView).text.toString()
            val name = (view.findViewById(R.id.tv_name) as TextView).text.toString()
            val publisher = (view.findViewById(R.id.tv_publisher) as TextView).text.toString()
            val platform = (view.findViewById(R.id.tv_platform) as TextView).text.toString()
            val price = (view.findViewById(R.id.tv_price) as TextView).text.toString()

            Toast.makeText(
                applicationContext,
                "S no : " + sid + "\n"
                        + "Name : " + name + "\n"
                        + "Publisher : " + publisher + "\n"
                        + "Platform : " + platform + "\n"
                        + "Price : " + price
                        + "DELETED", Toast.LENGTH_SHORT
            ).show()
            viewModel.onDelete(listview.getItemAtPosition(position) as VideoGame)

        })


        getAllPlatforms().forEach{
            adapter1.add(it)
        }

        spinner=findViewById(R.id.spinner)
    spinner.adapter=adapter1
    spinner.onItemSelectedListener=viewModel

    }
}
