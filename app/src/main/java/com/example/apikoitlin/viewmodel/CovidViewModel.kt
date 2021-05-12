package com.example.apikoitlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apikoitlin.model.City
import com.example.apikoitlin.model.network.CovidRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CovidViewModel: ViewModel() {

    private val cd = CompositeDisposable()
    private val covidRetrofit = CovidRetrofit()
    //city livedata
    val liveData = MutableLiveData<List<City>>()

    fun getNumbers(){

        cd.add(
            covidRetrofit.getCovidNumbers()
                .subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    //0 = USA and get cities from there
                    liveData.postValue(response.data[0].region.cities)
                    cd.clear()

                }, { throwable ->

                    cd.clear()
                    Log.d("TAG_X", throwable.toString())

                })
        )

    }

    override fun onCleared() {
        cd.clear()
        super.onCleared()
    }
}