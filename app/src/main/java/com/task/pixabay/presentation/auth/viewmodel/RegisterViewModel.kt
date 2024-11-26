package com.task.pixabay.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.pixabay.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: RegisterUseCase) : ViewModel() {

    private val _registerResult = MutableLiveData<com.task.pixabay.data.util.Result<Boolean>>()
    val registerResult: LiveData<com.task.pixabay.data.util.Result<Boolean>> = _registerResult

    fun registerUser(email: String, password: String, age: Int) {
        viewModelScope.launch {
            useCase.execute(email, password, age).collectLatest {
                _registerResult.value = it
            }
        }
    }
}