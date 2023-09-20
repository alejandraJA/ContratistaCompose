package com.example.contratistacompose.domain

import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Budget
import com.example.contratistacompose.data.source.web.models.Part

interface BudgetRepository {
    suspend fun getById(token: String, id: String, webStatus: WebStatus<Budget>)
    suspend fun createPart(token: String, idBudget: String, webStatus: WebStatus<List<Part>>)
}