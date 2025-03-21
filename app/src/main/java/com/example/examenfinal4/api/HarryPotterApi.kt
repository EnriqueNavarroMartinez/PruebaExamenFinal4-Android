package com.example.examenfinal4.api

import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {

    @GET("characters")
    suspend fun getAllCharacters(): Response<List<HarryPotterCharacter>>
}