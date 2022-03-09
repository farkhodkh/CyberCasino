package ru.cybercasino.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import ru.cybercasino.ui.R

/**
 * Method gets languages list
 */
@Composable
fun getLanguagesList(): List<LanguageAndFlag> {
    val dataArray = stringArrayResource(id = R.array.languages_flags)
    return dataArray.map { line ->
        val items = line.split(" ")

        LanguageAndFlag(
            language = items.last(),
            flag = items.first(),
        )
    }
}


/**
 * Country identification class for phone list
 */
data class LanguageAndFlag(
    /**
     * language name
     */
    val language: String,
    /**
     * Country flag unicode
     */
    val flag: String
)