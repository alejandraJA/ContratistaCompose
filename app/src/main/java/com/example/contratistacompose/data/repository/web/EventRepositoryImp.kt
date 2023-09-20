package com.example.contratistacompose.data.repository.web

import com.example.contratistacompose.data.repository.web.utils.Resolve
import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Event
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.EventRepository
import javax.inject.Inject

class EventRepositoryImp @Inject constructor(private val service: Service) : EventRepository {
    override suspend fun getAll(
        token: String,
        webStatus: WebStatus<List<Event>>
    ): Unit = Resolve(service.getAllEvents(token), webStatus).invoke()
}