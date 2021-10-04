package com.alkesh.ecommerce.presentation.screens.screen2.viewModel

import androidx.lifecycle.MutableLiveData
import com.alkesh.ecommerce.common.base.viewModel.BaseViewModel
import com.alkesh.ecommerce.common.repository.CategoryRepository
import com.alkesh.ecommerce.dataProvider.network.models.dto.CategoryDTO
import com.alkesh.ecommerce.dataProvider.network.models.dto.ImageDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    BaseViewModel() {
    val listImages = MutableLiveData<ArrayList<ImageDTO>>()

    init {
        getData()
    }

    private fun getCategories() {
        isLoading.value = true
        showMessage.value = null
        coroutineScope.launch {
            val result = categoryRepository.getImages()
            isLoading.value = false
            result?.let {
                if (it.successful) {
                    listImages.value = result.data
                } else {
                    showMessage.value = result.message
                }
            }
        }
    }

    fun getData() {
        getCategories()
    }
}