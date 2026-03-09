package edu.nd.pmcburne.hwapp.one.domain

import edu.nd.pmcburne.hwapp.one.data.remote.dto.GameDto
import edu.nd.pmcburne.hwapp.one.data.local.GameEntity

object GameMapper {

    private fun mapStatus(gameState: String): GameStatus {
        return when (gameState.lowercase()) {
            "pre" -> GameStatus.PRE
            "live" -> GameStatus.LIVE
            "final", "post" -> GameStatus.FINAL
            else -> GameStatus.PRE
        }
    }

    fun dtoToEntity(dto: GameDto, dateKey: String, gender: Gender): GameEntity {
        return GameEntity(
            dateKey = dateKey,
            gender = gender.name,
            gameId = dto.gameID,
            awayTeam = dto.away.names.short,
            homeTeam = dto.home.names.short,
            awayScore = dto.away.score.toIntOrNull(),
            homeScore = dto.home.score.toIntOrNull(),
            gameState = dto.gameState,
            startTimeText = dto.startTime,
            periodText = dto.currentPeriod,
            clockText = dto.contestClock,
            awayWinner = dto.away.winner,
            homeWinner = dto.home.winner,
            updatedAtEpoch = System.currentTimeMillis()
        )
    }

    fun entityToGame(e: GameEntity): Game {
        return Game(
            gameId = e.gameId,
            awayTeam = e.awayTeam,
            homeTeam = e.homeTeam,
            status = mapStatus(e.gameState),
            awayScore = e.awayScore,
            homeScore = e.homeScore,
            startTimeText = e.startTimeText,
            periodText = e.periodText,
            clockText = e.clockText,
            awayWinner = e.awayWinner,
            homeWinner = e.homeWinner
        )
    }
}