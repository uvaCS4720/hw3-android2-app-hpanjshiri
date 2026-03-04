package edu.nd.pmcburne.hwapp.one.domain

import edu.nd.pmcburne.hwapp.one.data.remote.dto.GameDto

object GameMapper {

    private fun mapStatus(gameState: String): GameStatus {
        return when (gameState.lowercase()) {
            "pre" -> GameStatus.PRE
            "live" -> GameStatus.LIVE
            "final", "post" -> GameStatus.FINAL
            else -> GameStatus.PRE
        }
    }

    fun dtoToGame(dto: GameDto): Game {
        val aScore = dto.away.score.toIntOrNull()
        val hScore = dto.home.score.toIntOrNull()

        return Game(
            gameId = dto.gameID,
            awayTeam = dto.away.names.short,
            homeTeam = dto.home.names.short,
            status = mapStatus(dto.gameState),
            awayScore = aScore,
            homeScore = hScore,
            startTimeText = dto.startTime,
            periodText = dto.currentPeriod,
            clockText = dto.contestClock,
            awayWinner = dto.away.winner,
            homeWinner = dto.home.winner
        )
    }
}