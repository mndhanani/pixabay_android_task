package com.task.pixabay.presentation.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // Observable fields for Data Binding
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    // Handle login button click
    fun onLoginClicked() {

    }
}