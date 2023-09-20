package com.example.contratistacompose.data.source.web.models

data class Availability(
    val available: Int,
    val idInventory: String,
    val idProduct: String,
    val quantity: Int,
    val reserved: Int
)