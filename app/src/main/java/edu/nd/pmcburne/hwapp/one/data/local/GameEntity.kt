package edu.nd.pmcburne.hwapp.one.data.local

import androidx.room.Entity

@Entity(
    tableName = "games",
    primaryKeys = ["dateKey", "gender", "gameId"]
)
data class GameEntity(
    val dateKey: String,   // yyyy-MM-dd
    val gender: String,    // MEN/WOMEN
    val gameId: String,

    val awayTeam: String,
    val homeTeam: String,

    val awayScore: Int?,
    val homeScore: Int?,

    val gameState: String,
    val startTimeText: String?,
    val periodText: String?,
    val clockText: String?,

    val awayWinner: Boolean,
    val homeWinner: Boolean,

    val updatedAtEpoch: Long
)