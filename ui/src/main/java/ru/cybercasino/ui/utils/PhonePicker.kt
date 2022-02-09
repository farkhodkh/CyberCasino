package ru.cybercasino.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import ru.cybercasino.ui.R

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

data class CountryCodeAndFlag(
    val code: String,
    val flag: String,
    val codeAndFlag: String
)

val defaultCountryData = CountryCodeAndFlag(
    code = "+7",
    flag = "\uD83C\uDDF7\uD83C\uDDFA",
    codeAndFlag = "\uD83C\uDDF7\uD83C\uDDFA +7"
)