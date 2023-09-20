package com.example.contratistacompose.data.source.web.models

data class Vendor(
    val id: String,
    val name: String,
    var address: Address? = null
)