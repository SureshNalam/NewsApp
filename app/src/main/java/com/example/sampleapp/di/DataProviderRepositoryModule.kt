package com.example.sampleapp.di

import com.example.sampleapp.api.NetworkApiServiceImpl
import com.example.sampleapp.repo.DataProviderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataProviderRepositoryModule {
    @Provides
    fun provideDataProviderRepository(networkApiServiceImpl: NetworkApiServiceImpl): DataProviderRepository {
        return DataProviderRepository(networkApiServiceImpl)
    }
}