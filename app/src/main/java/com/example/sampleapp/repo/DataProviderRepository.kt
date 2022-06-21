package com.example.sampleapp.repo

import com.example.sampleapp.api.NetworkApiServiceImpl
import com.example.sampleapp.models.TopHeadLines
import retrofit2.Response
import javax.inject.Inject

class DataProviderRepository @Inject constructor(private val networkApiServiceImpl: NetworkApiServiceImpl) {

    suspend fun getTopHeadLines(countryName: String = "In"): Response<TopHeadLines> {
        return networkApiServiceImpl.getTopHeadLines(countryName)
    }
}