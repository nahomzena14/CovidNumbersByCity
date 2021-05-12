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
    private val covidAdapter = CovidAdapter(listOf(), { city: City -> onCityClicked(city) })
    private lateinit var  detailsFragment:DetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.liveData.observe(this,{list ->
            covidAdapter.updateNumbers(list)
        })

        covid_recyclerview.adapter = covidAdapter
        viewModel.getNumbers()

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(covid_recyclerview)
    }

    private fun onCityClicked(city: City){
        detailsFragment = DetailsFragment(city)
        supportFragmentManager.beginTransaction()
            .replace(R.id.details_layout,detailsFragment)
            .addToBackStack(detailsFragment.tag)
            .commit()
    }
}