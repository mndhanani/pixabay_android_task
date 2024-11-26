package com.task.pixabay.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.domain.usecase.PixabayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PixabayViewModel @Inject constructor(
    private val pixabayUseCase: PixabayUseCase,
) : ViewModel() {

    private val _images = pixabayUseCase.execute()
    val images: Flow<PagingData<PixabayImage>> =
        _images.cachedIn(viewModelScope) // Cache the data in viewModelScope to handle lifecycle changes
}
