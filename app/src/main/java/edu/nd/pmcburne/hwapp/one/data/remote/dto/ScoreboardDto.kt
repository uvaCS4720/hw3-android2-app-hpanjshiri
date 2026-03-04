package edu.nd.pmcburne.hwapp.one.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScoreboardDto(
    val games: List<GameWrapperDto> = emptyList()
)

@JsonClass(generateAdapter = true)
data class GameWrapperDto(
    val game: GameDto
)

@JsonClass(generateAdapter = true)
data class GameDto(
    val gameID: String,
    val gameState: String,      // pre/live/final/post etc
    val startTime: String?,
    val contestClock: String?,
    val currentPeriod: String?,
    val home: TeamSideDto,
    val away: TeamSideDto
)

@JsonClass(generateAdapter = true)
data class TeamSideDto(
    val score: String,
    val winner: Boolean,
    val names: TeamNamesDto
)

@JsonClass(generateAdapter = true)
data class TeamNamesDto(
    val short: String
)