package com.example.apikoitlin.model

data class Region(
    val cities: List<City>,
    val iso: String,
    val lat: String,
    val long: String,
    val name: String,
    val province: String
)