package edu.nd.pmcburne.hwapp.one.ui.theme

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateStuff {
    private val keyFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val prettyFmt = DateTimeFormatter.ofPattern("MMM d, yyyy")

    fun todayKey(): String = LocalDate.now().format(keyFmt)

    fun pretty(dateKey: String): String {
        val d = LocalDate.parse(dateKey, keyFmt)
        return d.format(prettyFmt)
    }
}