package ru.cybercasino.feature.main.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.RoundingMode
import kotlin.math.roundToInt
import ru.cybercasino.ui.*
import ru.cybercasino.ui.R

@Composable
fun PaymentSystemsScreen(
    listPaymentSystems: List<Int>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {

        val boxWithConstraintsScope = this
        val itemWidth = boxWithConstraintsScope.maxWidth / 3 - 15.dp
        val rowsNumber = (listPaymentSystems.size / 3.0).toBigDecimal().setScale(0, RoundingMode.UP).toInt()

        val mListPaymentSystems = listPaymentSystems.toMutableList()
        Column {
            (1..rowsNumber).forEach {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 8.dp)
                        .background(Black)
                        .fillMaxWidth()
                ) {
                    val listToShow = mListPaymentSystems.take(3)
                    mListPaymentSystems.removeAll(listToShow)

                    listToShow.forEachIndexed { _, imageId ->
                        PaymentSystems(
                            imageResourceId = imageId,
                            modifier = Modifier
                                .width(itemWidth - 15.dp)
                                .height(itemWidth.times(0.4f))
                                .weight(0.33f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentSystems(
    imageResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlue),
        modifier = Modifier
            .padding(start = 8.dp)
            .then(modifier)
    )
    {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

val listOfMainPaymentSystems = listOf(
    R.drawable.background_paying_systems_visa,
    R.drawable.background_paying_systems_mc,
    R.drawable.background_paying_systems_wc,
    R.drawable.background_paying_systems_pp,
    R.drawable.background_paying_systems_rp,
    R.drawable.background_paying_systems_bc,
)

val listOfAdditionalPaymentSystems = listOf(
    R.drawable.background_paying_systems_sm,
    R.drawable.background_paying_systems_qs,
    R.drawable.background_paying_systems_kg,
    R.drawable.background_paying_systems_eg,
    R.drawable.background_paying_systems_bpg,
    R.drawable.background_paying_systems_gshy,
    R.drawable.background_paying_systems_cb,
    R.drawable.background_paying_systems_tk,
    R.drawable.background_paying_systems_bs,
    R.drawable.background_paying_systems_br,
    R.drawable.background_paying_systems_rg,
    R.drawable.background_paying_systems_gt,
    R.drawable.background_paying_systems_fg,
    R.drawable.background_paying_systems_ne,
    R.drawable.background_paying_systems_pt,
    R.drawable.background_paying_systems_ng,
    R.drawable.background_paying_systems_gv,
    R.drawable.background_paying_systems_og,
    R.drawable.background_paying_systems_og,
)

@Suppress("UnusedPrivateMember")
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PaymentSystemsScreenPreview() {
    PaymentSystemsScreen(
        listPaymentSystems = listOfMainPaymentSystems
    )
}