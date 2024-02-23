package com.globant.data.api

import com.globant.data.repository.Character
import com.globant.data.repository.Spell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApi {
    @GET("characters")
    suspend fun allCharacters(): Response<List<Character>>

    @GET("character/{id}")
    suspend fun characterById(@Path("id") id: String): Response<List<Character>>

    @GET("characters/students")
    suspend fun students(): Response<List<Character>>

    @GET("characters/house/{house}")
    suspend fun studentsByHouse(@Path("house") id: String): Response<List<Character>>

    @GET("spells")
    suspend fun spells(): Response<List<Spell>>

    @GET("characters/staff")
    suspend fun staff(): Response<List<Character>>
}