package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hwapp.one.data.repo.ScoresRepository
import edu.nd.pmcburne.hwapp.one.domain.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

class ScoresViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = ScoresRepository(app.applicationContext)

    private val _state = MutableStateFlow(
        ScoresUiState(
            selectedDateKey = DateStuff.todayKey(),
            gender = Gender.MEN
        )
    )
    val state: StateFlow<ScoresUiState> = _state

    private var observeJob: Job? = null

    init {
        startObserving()
        refresh()
    }

    private fun startObserving() {
        observeJob?.cancel()
        observeJob = viewModelScope.launch {
            val s = _state.value
            repo.observeGames(s.selectedDateKey, s.gender).collectLatest { games ->
                _state.value = _state.value.copy(games = games)
            }
        }
    }

    fun toggleGender() {
        val newGender = if (_state.value.gender == Gender.MEN) Gender.WOMEN else Gender.MEN
        _state.value = _state.value.copy(gender = newGender, lastError = null)
        startObserving()
        refresh()
    }

    fun setDate(dateKey: String) {
        _state.value = _state.value.copy(selectedDateKey = dateKey, lastError = null)
        startObserving()
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, lastError = null)
            try {
                val didNetwork = repo.refreshToDb(_state.value.selectedDateKey, _state.value.gender)
                _state.value = _state.value.copy(isOffline = !didNetwork)
            } catch (e: Exception) {
                _state.value = _state.value.copy(lastError = e.message ?: "Refresh failed")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}