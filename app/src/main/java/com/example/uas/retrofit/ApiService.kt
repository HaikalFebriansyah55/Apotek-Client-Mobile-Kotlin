package com.example.uas.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("characters")
    suspend fun getAllCharacterData(): List<ResponseItem>

    @GET("obat")
    suspend fun getAllObatData(): List<ObatResponseItem>
}


