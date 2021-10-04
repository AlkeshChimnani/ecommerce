package com.alkesh.ecommerce.common.repository

import com.alkesh.ecommerce.common.base.repository.BaseRepository
import com.alkesh.ecommerce.dataProvider.network.models.response.GetCategoriesApiResponse
import com.alkesh.ecommerce.dataProvider.network.models.response.GetImagesApiResponse
import com.alkesh.ecommerce.dataProvider.network.services.CategoryService
import javax.inject.Inject


open class CategoryRepository @Inject constructor(
    private val categoryService: CategoryService
) : BaseRepository() {


    suspend fun getCategories(): GetCategoriesApiResponse? {
        var model: GetCategoriesApiResponse? = null
        try {
            val response = categoryService.getCategories()
            if (response.isSuccessful) {
                model = response.body()
                model?.let {
                    it.successful = true
                }
            }
        } catch (exp: Exception) {
            model = GetCategoriesApiResponse()
            model.let {
                it.successful = false
                it.message = exp.message
            }

        }
        return model
    }

    suspend fun getImages(): GetImagesApiResponse? {
        var model: GetImagesApiResponse? = null
        try {
            val response = categoryService.getImages()
            if (response.isSuccessful) {
                model = response.body()
                model?.let {
                    it.successful = true
                }
            }
        } catch (exp: Exception) {
            model = GetImagesApiResponse()
            model.let {
                it.successful = false
                it.message = exp.message
            }

        }
        return model
    }

}

enum class WidgetType(var value: String) {
    IMAGE("image"),
    CAROUSEL("carousel"),
    SLIDER("slider")
}

