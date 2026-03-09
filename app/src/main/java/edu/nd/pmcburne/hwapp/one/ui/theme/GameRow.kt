package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.nd.pmcburne.hwapp.one.domain.GameStatus
import edu.nd.pmcburne.hwapp.one.domain.Game
import androidx.compose.ui.text.font.FontWeight

@Composable
fun GameRow(game: Game) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = "${game.awayTeam} @ ${game.homeTeam}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            when (game.status) {
                GameStatus.PRE -> {
                    Text("Upcoming", style = MaterialTheme.typography.bodyMedium)
                    Text("Start: ${game.startTimeText ?: "TBD"}", style = MaterialTheme.typography.bodySmall)
                }

                GameStatus.LIVE -> {
                    Text("Live", style = MaterialTheme.typography.bodyMedium)

                    val a = game.awayScore?.toString() ?: "-"
                    val h = game.homeScore?.toString() ?: "-"
                    Text("Score: ${game.awayTeam} $a  •  ${game.homeTeam} $h", style = MaterialTheme.typography.bodySmall)

                    val period = game.periodText ?: "?"
                    val clock = game.clockText ?: ""
                    Text("Time: $period ${clock}".trim(), style = MaterialTheme.typography.bodySmall)
                }

                GameStatus.FINAL -> {
                    Text("Final", style = MaterialTheme.typography.bodyMedium)

                    val a = game.awayScore?.toString() ?: "-"
                    val h = game.homeScore?.toString() ?: "-"
                    Text("Final: ${game.awayTeam} $a  •  ${game.homeTeam} $h", style = MaterialTheme.typography.bodySmall)

                    val winner = when {
                        game.awayWinner == true -> game.awayTeam
                        game.homeWinner == true -> game.homeTeam
                        else -> null
                    }
                    if (winner != null) {
                        Spacer(Modifier.height(4.dp))
                        Text("Winner: $winner", fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}