package com.task.pixabay.data.repository

import com.task.pixabay.data.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository for handling authentication-related operations.
 *
 * Provides mock implementations for login and registration processes.
 */
class AuthRepository @Inject constructor() {

    suspend fun mockLogin(email: String, password: String): Flow<Result<Boolean>> {
        return flow {
            emit(Result.loading())

            // Simulate network delay
            delay(1000)

            emit(Result.success(true))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun mockRegister(email: String, password: String, age: Int): Flow<Result<Boolean>> {
        return flow {
            emit(Result.loading())

            // Simulate network delay
            delay(1000)

            emit(Result.success(true))
        }.flowOn(Dispatchers.IO)
    }
}