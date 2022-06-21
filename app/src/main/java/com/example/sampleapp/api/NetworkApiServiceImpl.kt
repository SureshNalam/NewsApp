package com.example.sampleapp.api

import com.example.sampleapp.models.TopHeadLines
import retrofit2.Response
import javax.inject.Inject

class NetworkApiServiceImpl @Inject constructor(private val networkApiService: NetworkApiService) {
    suspend fun getTopHeadLines(countryName: String): Response<TopHeadLines> =
        networkApiService.getTopHeadLines(countryName)
}