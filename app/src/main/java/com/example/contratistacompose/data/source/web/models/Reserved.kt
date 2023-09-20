package com.example.contratistacompose.data.source.web.models

data class Reserved(
    val id: String,
    val dateExpiry: String,
    val price: Price,
    var inventory: ProductInventory
)