package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hwapp.one.data.repo.ScoresRepository
import edu.nd.pmcburne.hwapp.one.domain.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScoresViewModel : ViewModel() {

    private val repo = ScoresRepository()

    private val _state = MutableStateFlow(
        ScoresUiState(
            selectedDateKey = DateStuff.todayKey(),
            gender = Gender.MEN
        )
    )
    val state: StateFlow<ScoresUiState> = _state

    init {
        refresh() // initial load
    }

    fun toggleGender() {
        val newGender = if (_state.value.gender == Gender.MEN) Gender.WOMEN else Gender.MEN
        _state.value = _state.value.copy(gender = newGender)
        refresh()
    }

    fun setDate(dateKey: String) {
        _state.value = _state.value.copy(selectedDateKey = dateKey)
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, lastError = null)
            try {
                val games = repo.fetchGames(_state.value.selectedDateKey, _state.value.gender)
                _state.value = _state.value.copy(games = games)
            } catch (e: Exception) {
                _state.value = _state.value.copy(lastError = e.message ?: "Network error")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}