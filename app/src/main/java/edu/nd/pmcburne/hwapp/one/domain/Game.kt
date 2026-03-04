package edu.nd.pmcburne.hwapp.one.domain

data class Game(
    val gameId: String,
    val awayTeam: String,
    val homeTeam: String,
    val status: GameStatus,

    val awayScore: Int? = null,
    val homeScore: Int? = null,

    val startTimeText: String? = null, // PRE
    val periodText: String? = null,    // LIVE
    val clockText: String? = null,     // LIVE

    val awayWinner: Boolean? = null,
    val homeWinner: Boolean? = null
)