package ru.cybercasino.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.LightBlue
import ru.cybercasino.ui.Dark

/**
 * App default checkbox
 */
@Composable
fun SimpleCheckboxComponent(
    modifier: Modifier,
    titleResourceId: Int
) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checkedState.value,
            colors = CheckboxDefaults.colors(
                checkedColor = LightBlue,
                uncheckedColor = LightBlue,
                disabledColor = Dark,
            ),
            onCheckedChange = { checkedState.value = it },
        )
        Text(
            text = stringResource(id = titleResourceId),
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
