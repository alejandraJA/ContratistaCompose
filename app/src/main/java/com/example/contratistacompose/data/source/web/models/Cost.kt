package com.example.contratistacompose.data.source.web.models

data class Cost(
    val id: String,
    val unitCost: Double,
    val quantity: Int,
    var date: String,
    var vendor: Vendor?
)