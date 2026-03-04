package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.nd.pmcburne.hwapp.one.domain.GameStatus
import edu.nd.pmcburne.hwapp.one.domain.Game

@Composable
fun GameRow(game: Game) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "${game.awayTeam} @ ${game.homeTeam}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(6.dp))

            when (game.status) {
                GameStatus.PRE -> {
                    Text("Upcoming", style = MaterialTheme.typography.bodyMedium)
                    Text("Start: ${game.startTimeText ?: "TBD"}", style = MaterialTheme.typography.bodySmall)
                }
                GameStatus.LIVE -> {
                    Text("Live", style = MaterialTheme.typography.bodyMedium)
                    val a = game.awayScore?.toString() ?: "-"
                    val h = game.homeScore?.toString() ?: "-"
                    Text("Score: $a - $h", style = MaterialTheme.typography.bodySmall)
                    Text("${game.periodText ?: "?"} • ${game.clockText ?: ""}".trim(), style = MaterialTheme.typography.bodySmall)
                }
                GameStatus.FINAL -> {
                    Text("Final", style = MaterialTheme.typography.bodyMedium)
                    val a = game.awayScore?.toString() ?: "-"
                    val h = game.homeScore?.toString() ?: "-"
                    Text("Final: $a - $h", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}