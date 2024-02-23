package com.globant.data.repository

import com.globant.data.api.HarryPotterApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class HarryPotterRepositoryImpl @Inject constructor(
    private val api: HarryPotterApi
) : HarryPotterRepository {
    override suspend fun getAllCharacters(): ResponseCharacters {
        val response = api.allCharacters()
        return ResponseCharacters(
            response.body() ?: emptyList(),
            Service(
                code = response.code(),
                name = "Characters",
            )
        )
    }

    override suspend fun getStaff(): ResponseCharacters {
        val response = api.staff()
        return ResponseCharacters(
            response.body() ?: emptyList(),
            Service(
                code = response.code(),
                name = "Staff",
            )
        )
    }

    override suspend fun getCharacterById(id: String): ResponseCharacter {
        val response = api.characterById(id)
        return ResponseCharacter(
            response.body()?.first()!!,
            Service(
                code = response.code(),
                name = "Character by ID",
            )
        )
    }

    override suspend fun getAllStudents(): ResponseCharacters {
        val response = api.students()
        return ResponseCharacters(
            response.body() ?: emptyList(),
            Service(
                code = response.code(),
                name = "Students",
            )
        )
    }

    override suspend fun getStudentsByHouse(house: String): ResponseCharacters {
        val response = api.studentsByHouse(house)
        return ResponseCharacters(
            response.body() ?: emptyList(),
            Service(
                code = response.code(),
                name = "Students  by House: $house",
            )
        )

    }

    override suspend fun getSpells(): ResponseSpells {
        val response = api.spells()
        return ResponseSpells(
            response.body() ?: emptyList(),
            Service(
                code = response.code(),
                name = "Spells",
            )
        )
    }
}