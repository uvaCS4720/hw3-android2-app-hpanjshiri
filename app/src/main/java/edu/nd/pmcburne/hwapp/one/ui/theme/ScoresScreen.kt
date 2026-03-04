package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class FakeGame(
    val away: String,
    val home: String,
    val statusText: String,
    val detailText: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen() {
    val today = DateStuff.todayKey()

    val fakeGames = listOf(
        FakeGame("UVA", "VT", "Live", "Score: 33-46 • 2nd • 18:00"),
        FakeGame("Duke", "UNC", "Upcoming", "Start: 6:30 PM"),
        FakeGame("Kansas", "Baylor", "Final", "Final: 60-55")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Basketball Scores") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = { /* TODO later */ }) {
                    Text(DateStuff.pretty(today))
                }
                OutlinedButton(onClick = { /* TODO later */ }) {
                    Text("Men")
                }
                OutlinedButton(onClick = { println("refresh pressed (TODO)") }) {
                    Text("Refresh")
                }
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(fakeGames) { g ->
                    GameRow(
                        away = g.away,
                        home = g.home,
                        statusText = g.statusText,
                        detailText = g.detailText
                    )
                }
            }
        }
    }
}