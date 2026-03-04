package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.nd.pmcburne.hwapp.one.domain.GameStatus
import edu.nd.pmcburne.hwapp.one.domain.Gender
import edu.nd.pmcburne.hwapp.one.domain.Game

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen() {
    val state = ScoresUiState(
        selectedDateKey = DateStuff.todayKey(),
        gender = Gender.MEN,
        games = listOf(
            Game("1", "UVA", "VT", GameStatus.LIVE, awayScore = 37, homeScore = 41, periodText = "2nd", clockText = "17:30"),
            Game("2", "Duke", "UNC", GameStatus.PRE, startTimeText = "7:00 PM"),
            Game("3", "Kansas", "Baylor", GameStatus.FINAL, awayScore = 65, homeScore = 60)
        )
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Basketball Scores") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = { /* TODO */ }) {
                    Text(DateStuff.pretty(state.selectedDateKey))
                }
                OutlinedButton(onClick = { /* TODO */ }) {
                    Text(state.gender.label)
                }
                OutlinedButton(onClick = { println("refresh pressed (TODO)") }) {
                    Text("Refresh")
                }
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.games) { g ->
                    GameRow(g)
                }
            }
        }
    }
}