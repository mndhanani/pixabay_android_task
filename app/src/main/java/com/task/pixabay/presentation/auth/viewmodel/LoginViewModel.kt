package com.task.pixabay.presentation.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.pixabay.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: LoginUseCase) : ViewModel() {

    private val _loginResult = MutableLiveData<com.task.pixabay.data.util.Result<Boolean>>()
    val loginResult: LiveData<com.task.pixabay.data.util.Result<Boolean>> = _loginResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            useCase.execute(email, password).collectLatest {
                _loginResult.value = it
            }
        }
    }
}