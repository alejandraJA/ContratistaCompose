package com.example.contratistacompose.data.source.web.models

data class Schedule(
    val address: Address,
    val date: String,
    val id: String,
    val name: String,
    val note: String,
    val state: String
)