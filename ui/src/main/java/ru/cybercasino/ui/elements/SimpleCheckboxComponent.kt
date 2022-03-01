@file:OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)

package ru.cybercasino.ui.elements

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.cybercasino.ui.Dark
import ru.cybercasino.ui.LightBlue

/**
 * App default checkbox
 */
@Composable
fun SimpleCheckboxComponent(
    modifier: Modifier,
    titleResourceId: Int,
    checkedStateValue: MutableState<Boolean>,
    onCheckedChanged: (Boolean) -> Unit
) {
    val checkedState = remember { checkedStateValue }
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
                checkmarkColor = LightBlue,
            ),
            onCheckedChange = {
                onCheckedChanged(it)
                checkedState.value = it
            }
        )
        Text(
            text = stringResource(id = titleResourceId),
            modifier = Modifier
                .padding(start = 12.dp)
        )
    }
}
