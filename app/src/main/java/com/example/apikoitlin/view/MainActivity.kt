package com.example.apikoitlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.apikoitlin.R
import com.example.apikoitlin.model.City
import com.example.apikoitlin.viewmodel.CovidViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LifecycleOwner {

    private val viewModel: CovidViewModel by viewModels()
    private val covidAdapter = CovidAdapter(listOf()) { city: City -> onCityClicked(city) }
    private lateinit var  detailsFragment:DetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get data
        viewModel.liveData.observe(this,{list ->
            covidAdapter.updateNumbers(list)
        })

        //set adapter for recycler view
        covid_recyclerview.adapter = covidAdapter
        viewModel.getNumbers()

        //Snap Helper
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(covid_recyclerview)
    }

    //cover the main activity with a fragment
    private fun onCityClicked(city: City){
        detailsFragment = DetailsFragment(city)
        supportFragmentManager.beginTransaction()
                //replace place holder in main activity with fragment
            .replace(R.id.details_layout,detailsFragment)
            .addToBackStack(detailsFragment.tag)
            .commit()
    }
}