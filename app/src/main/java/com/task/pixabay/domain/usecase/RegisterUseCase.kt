package com.task.pixabay.domain.usecase

import com.task.pixabay.data.repository.AuthRepository
import com.task.pixabay.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for executing registration operations.
 *
 * Delegates the registration process to the AuthRepository and handles the
 * resulting data flow.
 */
class RegisterUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun execute(email: String, password: String, age: Int): Flow<Result<Boolean>> {
        return repository.mockRegister(email, password, age)
    }
}