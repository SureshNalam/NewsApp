package com.example.sampleapp.models

data class TopHeadLines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)