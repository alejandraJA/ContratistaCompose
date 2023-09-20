package com.example.contratistacompose.service

import com.example.contratistacompose.data.source.web.models.Budget
import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.domain.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BudgetService @Inject constructor(
    private val repository: BudgetRepository,
    userService: UserService
) : SuperService(userService) {
    suspend fun getById(
        id: String,
        onSuccess: (Budget) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getById(token!!, id, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun createPart(
        budgetId: String,
        onSuccess: (List<Part>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
            if (isUserLogged) {
                repository.createPart(token!!, budgetId, getWebStatus(onSuccess, onError))
            }
        }
}