package com.alkesh.ecommerce.common.di

import com.alkesh.ecommerce.dataProvider.network.retrofitService.RetrofitService
import com.alkesh.ecommerce.dataProvider.network.services.CategoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    fun provideCategoryApiService(retrofitService: RetrofitService): CategoryService {
        return retrofitService.getInstance(CategoryService::class.java)
    }
}