package com.example.apikoitlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apikoitlin.R
import com.example.apikoitlin.model.City
import kotlinx.android.synthetic.main.covid_item_layout.view.*

//adapter for recycler view
class CovidAdapter(private var covidList:List<City>,var cl:(City)->Unit) :
    RecyclerView.Adapter<CovidAdapter.CovidViewHolder>()  {

    inner class CovidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {

        //inflate the recycler view
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.covid_item_layout,parent,false)
        return CovidViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {

        //set values for the recycler view
        covidList[position].let{
            holder.itemView.apply {
                city_textview.text = it.name
                date_textview.text = "Latest: "+ it.date
            }
            holder.itemView.setOnClickListener { cl(covidList[position]) }        }

    }

    override fun getItemCount(): Int {
        return covidList.size
    }

    fun updateNumbers(list:List<City>){
        this.covidList = list
        notifyDataSetChanged()
    }

}