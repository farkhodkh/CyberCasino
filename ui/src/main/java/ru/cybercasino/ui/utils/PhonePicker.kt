package ru.cybercasino.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import ru.cybercasino.ui.R

/**
 * method gets countries list
 */
@Composable
fun getCountriesList(): List<CountryCodeAndFlag> {
    val dataArray = stringArrayResource(id = R.array.country_codes_flags)

    return dataArray.map { line ->
        val items = line.split(" ")

        CountryCodeAndFlag(
            code = items.last(),
            flag = items.first(),
            codeAndFlag = line
        )
    }
}

/**
 * Country identification class for phone list
 */
data class CountryCodeAndFlag(
    /**
     * Country phone code
     */
    val code: String,
    /**
     * Country flag unicode
     */
    val flag: String,
    /**
     * Country phone and flag
     */
    val codeAndFlag: String
)

/**
 * Country item by default
 */
val defaultCountryData = CountryCodeAndFlag(
    code = "+7",
    flag = "\uD83C\uDDF7\uD83C\uDDFA",
    codeAndFlag = "\uD83C\uDDF7\uD83C\uDDFA +7"
)