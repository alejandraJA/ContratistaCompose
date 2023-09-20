package com.example.contratistacompose.data.repository.web

import com.example.contratistacompose.data.repository.web.utils.Resolve
import com.example.contratistacompose.data.repository.web.utils.WebStatus
import com.example.contratistacompose.data.source.web.models.Budget
import com.example.contratistacompose.data.source.web.models.Part
import com.example.contratistacompose.data.source.web.retrofit.Service
import com.example.contratistacompose.domain.BudgetRepository
import javax.inject.Inject

class BudgetRepositoryImp @Inject constructor(private val service: Service) : BudgetRepository {
    override suspend fun getById(token: String, id: String, webStatus: WebStatus<Budget>) =
        Resolve(service.getBudgetById(token, id), webStatus).invoke()

    override suspend fun createPart(token: String, idBudget: String, webStatus: WebStatus<List<Part>>) =
        Resolve(service.createPart(token, idBudget), webStatus).invoke()
}