package com.example.contratistacompose.data.source.web.models

data class ResponseApi<T>(
    val status: Boolean,
    val message: String,
    val data: T?,
)
