package com.task.pixabay.domain.usecase

import com.task.pixabay.data.repository.AuthRepository
import com.task.pixabay.data.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend fun execute(email: String, password: String): Flow<Result<Boolean>> {
        return repository.mockLogin(email, password)
    }
}