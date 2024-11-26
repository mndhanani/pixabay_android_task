package com.task.pixabay.data.repository

import com.task.pixabay.data.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor() {

    suspend fun mockLogin(email: String, password: String): Flow<Result<Boolean>> {
        return flow {
            emit(Result.loading())

            // Simulate network delay
            delay(1000)

            emit(Result.success(true))
        }.flowOn(Dispatchers.IO)
    }
}