package com.example.contratistacompose.domain

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Event

interface EventRepository {
    suspend fun getAll(token: String, webStatus: WebStatus<List<Event>>)
}