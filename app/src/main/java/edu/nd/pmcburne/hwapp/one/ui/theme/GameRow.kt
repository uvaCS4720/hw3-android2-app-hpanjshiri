package edu.nd.pmcburne.hwapp.one.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameRow(
    away: String,
    home: String,
    statusText: String,
    detailText: String
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "$away @ $home",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(6.dp))
            Text(text = statusText, style = MaterialTheme.typography.bodyMedium)
            Text(text = detailText, style = MaterialTheme.typography.bodySmall)
        }
    }
}