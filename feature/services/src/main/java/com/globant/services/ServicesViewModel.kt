package com.globant.services

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.data.repository.HarryPotterRepository
import com.globant.data.repository.ResponseCharacter
import com.globant.data.repository.ResponseCharacters
import com.globant.data.repository.ResponseSpells
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@Immutable
data class UiState(
    val characters: ResponseCharacters? = null,
    val characterById: ResponseCharacter? = null,
    val students: ResponseCharacters? = null,
    val studentsByHouse: ResponseCharacters? = null,
    val spells: ResponseSpells? = null,
    val staff: ResponseCharacters? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
)

@HiltViewModel
class ServicesViewModel @Inject constructor(
    private val repository: HarryPotterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchServiceStatus()
    }

    fun fetchServiceStatus() {
        getAllCharacters()
        getAllStudents()
        getSpells()
        getStaff()
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            val characters = repository.getAllCharacters()
            _uiState.update { it.copy(characters = characters) }
            val randomCharacter = (0..characters.list.size.minus(1)).random()
            characterById(characters.list[randomCharacter].id)

        }
    }

    private fun characterById(id: String) {
        viewModelScope.launch {
            val character = repository.getCharacterById(id)
            _uiState.update { it.copy(characterById = character) }
        }
    }

    private fun getAllStudents() {
        viewModelScope.launch {
            val students = repository.getAllStudents()
            _uiState.update { it.copy(students = students) }
            val randomCharacter = (0..students.list.size.minus(1)).random()
            studentByHouse(students.list[randomCharacter].house)
        }
    }

    private fun studentByHouse(house: String) {
        viewModelScope.launch {
            val student = repository.getStudentsByHouse(house)
            _uiState.update { it.copy(studentsByHouse = student) }
        }
    }

    private fun getSpells() {
        viewModelScope.launch {
            val spells = repository.getSpells()
            _uiState.update { it.copy(spells = spells) }
        }
    }

    private fun getStaff() {
        viewModelScope.launch {
            val staff = repository.getStaff()
            _uiState.update { it.copy(staff = staff) }
        }
    }

}