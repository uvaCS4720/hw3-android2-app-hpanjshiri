package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.lifecycle.ViewModel
import edu.nd.pmcburne.hwapp.one.domain.Gender
import edu.nd.pmcburne.hwapp.one.domain.Game
import edu.nd.pmcburne.hwapp.one.domain.GameStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScoresViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        ScoresUiState(
            selectedDateKey = DateStuff.todayKey(),
            gender = Gender.MEN,
            games = demoGames()
        )
    )
    val state: StateFlow<ScoresUiState> = _state

    fun toggleGender() {
        val newGender = if (_state.value.gender == Gender.MEN) Gender.WOMEN else Gender.MEN
        _state.value = _state.value.copy(gender = newGender)
        // still demo data for now
    }

    fun setDate(dateKey: String) {
        _state.value = _state.value.copy(selectedDateKey = dateKey)
        // still demo data for now
    }

    fun refresh() {
        // still fake (later real network)
        println("refresh in VM (TODO)")
    }

    private fun demoGames(): List<Game> {
        return listOf(
            Game("1", "UVA", "VT", GameStatus.LIVE, awayScore = 37, homeScore = 41, periodText = "2nd", clockText = "18:00"),
            Game("2", "Duke", "UNC", GameStatus.PRE, startTimeText = "6:30 PM"),
            Game("3", "Kansas", "Baylor", GameStatus.FINAL, awayScore = 60, homeScore = 55)
        )
    }
}