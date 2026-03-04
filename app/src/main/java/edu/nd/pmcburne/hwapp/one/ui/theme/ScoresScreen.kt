package edu.nd.pmcburne.hwapp.one.ui.theme

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoresScreen(vm: ScoresViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    val context = LocalContext.current

    fun openDatePicker() {
        val current = LocalDate.parse(state.selectedDateKey) // yyyy-MM-dd
        DatePickerDialog(
            context,
            { _, y, m, d ->
                val key = "%04d-%02d-%02d".format(y, m + 1, d)
                vm.setDate(key)
            },
            current.year, current.monthValue - 1, current.dayOfMonth
        ).show()
    }

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
                OutlinedButton(onClick = { openDatePicker() }) {
                    Text(DateStuff.pretty(state.selectedDateKey))
                }
                OutlinedButton(onClick = { vm.toggleGender() }) {
                    Text(state.gender.label)
                }
                OutlinedButton(onClick = { vm.refresh() }) {
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