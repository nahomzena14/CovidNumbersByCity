package com.example.apikoitlin.model

import com.example.apikoitlin.util.Constants.Companion.BASE_URL
import com.example.apikoitlin.util.Constants.Companion.END_POINT
import com.example.apikoitlin.util.Constants.Companion.REGION
import com.example.apikoitlin.util.Constants.Companion.REGION_VALUE
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class CovidRetrofit {

    private val covidService = createRetrofit().create(CovidService::class.java)

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getCovidNumbers(): Single<CovidResponse> =covidService.getNumbers(REGION_VALUE)

    interface CovidService {
        @GET(END_POINT)
        fun getNumbers(@Query(REGION) region:String): Single<CovidResponse>
    }

}