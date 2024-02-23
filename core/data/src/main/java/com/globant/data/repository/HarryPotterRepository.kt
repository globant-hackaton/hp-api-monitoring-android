package com.globant.data.repository

interface HarryPotterRepository {
    suspend fun getAllCharacters(): ResponseCharacters
    suspend fun getCharacterById(id: String): ResponseCharacter
    suspend fun getStaff(): ResponseCharacters
    suspend fun getAllStudents(): ResponseCharacters
    suspend fun getStudentsByHouse(house: String): ResponseCharacters
    suspend fun getSpells(): ResponseSpells
}

data class Service(
    val code: Int,
    val name: String,
    val status: ServiceStatus = ServiceStatus.entries.shuffled().first()
)

enum class ServiceStatus {
    SUCCESS,
    ERROR;
}

data class ResponseCharacters(
    val list: List<Character>,
    val service: Service
)

data class ResponseCharacter(
    val character: Character,
    val service: Service
)

data class ResponseSpells(
    val list: List<Spell>,
    val service: Service
)

data class Character(val id: String, val name: String, val house: String)
data class Spell(val id: String, val name: String, val description: String)

