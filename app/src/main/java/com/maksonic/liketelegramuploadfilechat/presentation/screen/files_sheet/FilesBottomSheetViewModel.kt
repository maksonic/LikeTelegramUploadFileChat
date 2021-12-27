package com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet

import android.app.Application
import android.database.ContentObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksonic.liketelegramuploadfilechat.domain.MediaStoreImage
import com.maksonic.liketelegramuploadfilechat.domain.ShowUserGalleryImagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Author: maksonic on 25.12.2021
 */
class FilesBottomSheetViewModel(
    private val useCase: ShowUserGalleryImagesUseCase,
    private val application: Application
) : ViewModel() {

    private val _images = MutableStateFlow<List<MediaStoreImage>>(emptyList())
    val images: StateFlow<List<MediaStoreImage>> = _images.asStateFlow()

    private val contentObserver: ContentObserver? = null

    fun showGalleryImages() {
        viewModelScope.launch {
            val imageList = useCase.queryImagesFromGallery()
            _images.value = imageList
        }
    }

    override fun onCleared() {
        super.onCleared()
        contentObserver?.let {
            application.contentResolver.unregisterContentObserver(it)
        }
    }
}