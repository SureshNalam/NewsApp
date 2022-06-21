package com.example.sampleapp.api

import com.example.sampleapp.models.TopHeadLines
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query(PARAM_COUNTRY) country: String,
        @Query(PARAM_API_KEY) apiKey: String = PARAM_API_KEY_VALUE
        ): Response<TopHeadLines>


    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val PARAM_COUNTRY = "country"
        const val PARAM_API_KEY = "apiKey"
        const val PARAM_API_KEY_VALUE = "8cc6e6c4a1e44f1ea14b9caf3496c300"

    }
}