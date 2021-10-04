package com.alkesh.ecommerce.dataProvider.network.services

import com.alkesh.ecommerce.dataProvider.network.models.response.GetCategoriesApiResponse
import com.alkesh.ecommerce.dataProvider.network.models.response.GetImagesApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface CategoryService {
    @GET("content")
    suspend fun getCategories(): Response<GetCategoriesApiResponse>

    @GET("list")
    suspend fun getImages(): Response<GetImagesApiResponse>


}