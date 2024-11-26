package com.task.pixabay.presentation.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    // Observable fields for Data Binding
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val age = MutableLiveData("")

    // Handle register button click
    fun onRegisterClicked() {

    }
}