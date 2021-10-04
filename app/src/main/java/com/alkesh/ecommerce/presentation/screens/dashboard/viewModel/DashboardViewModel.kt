package com.alkesh.ecommerce.presentation.screens.dashboard.viewModel

import androidx.lifecycle.MutableLiveData
import com.alkesh.ecommerce.common.base.viewModel.BaseViewModel
import com.alkesh.ecommerce.common.repository.CategoryRepository
import com.alkesh.ecommerce.dataProvider.network.models.dto.CategoryDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    BaseViewModel() {
    val listCategories = MutableLiveData<ArrayList<CategoryDTO>>()

    init {
        getData()
    }

    private fun getCategories() {
        isLoading.value = true
        showMessage.value = null
        coroutineScope.launch {
            val result = categoryRepository.getCategories()
            isLoading.value = false
            result?.let {
                if (it.successful) {
                    listCategories.value = result.data
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