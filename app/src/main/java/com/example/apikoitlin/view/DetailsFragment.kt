package com.example.apikoitlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.apikoitlin.R
import com.example.apikoitlin.model.City
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment(private val city: City):Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        f_city_name_textview.text = "City Name: "+city.name
        f_date_name_textview.text = "Date: "+city.date
        f_confirmed_cases_textview.text = "Confirmed Cases: "+city.confirmed.toString()
        f_death_textview.text = "Death: "+city.deaths.toString()
        f_fatility_textview.text = "Fatility %: 0."+city.fips.toString()
    }

}