package com.example.contratistacompose.service

import com.example.contratistacompose.data.source.web.models.Event
import com.example.contratistacompose.domain.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventService @Inject constructor(
    private val repository: EventRepository,
    userService: UserService
): SuperService(userService) {

    suspend fun getAll(
        onSuccess: (List<Event>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getAll(token!!, getWebStatus(onSuccess, onError))
        }
    }

}