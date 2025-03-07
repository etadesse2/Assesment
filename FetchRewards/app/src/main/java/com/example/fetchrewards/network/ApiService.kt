package com.example.fetchrewards.network

import retrofit2.http.GET
import retrofit2.Response

interface ApiService {
    @GET("hiring.json")  // Specifies the full path after the base URL
    suspend fun fetchItems(): Response<List<Item>>
}


