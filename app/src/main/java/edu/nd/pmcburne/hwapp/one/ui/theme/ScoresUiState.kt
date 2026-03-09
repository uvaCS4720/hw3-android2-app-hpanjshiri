package edu.nd.pmcburne.hwapp.one.ui.theme

import edu.nd.pmcburne.hwapp.one.domain.Gender
import edu.nd.pmcburne.hwapp.one.domain.Game

data class ScoresUiState(
    val selectedDateKey: String,
    val gender: Gender,
    val games: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val lastError: String? = null,
    val isOffline: Boolean = false
)