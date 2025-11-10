package com.chargeback.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.ui.theme.ChargebackAndroidAppTheme
import com.chargeback.app.ui.theme.TextW400
import com.chargeback.app.ui.theme.TextW500
import com.chargeback.app.utils.Constants
import com.chargeback.app.utils.IconOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogServices(
    onDismissRequest: () -> Unit,
    onSubmit: (IconOption) -> Unit,
    selectedService: String = ""
) {
    var currentSelection by rememberSaveable { mutableStateOf(selectedService) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(selectedService) {
        currentSelection = selectedService
    }

    val sheetState = if (LocalInspectionMode.current) {
        rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    } else {
        rememberModalBottomSheetState(skipPartiallyExpanded = true)
    }

    ModalBottomSheet(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        onDismissRequest = {
            onDismissRequest()
        },
        containerColor = colorResource(R.color.white),
        shape = RoundedCornerShape(8.dp, 8.dp),
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = colorResource(R.color.neutral200)
            )
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextW500(
                    modifier = Modifier,
                    text = stringResource(R.string.done),
                    fontColor = colorResource(R.color.transparent),
                    fontSize = 18.sp
                )
                TextW500(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                    text = stringResource(R.string.choose_a_service),
                    fontColor = colorResource(R.color.baseBlack),
                    fontSize = 18.sp
                )
                TextW500(
                    modifier = (if (currentSelection.isNotBlank()) {
                        Modifier.clickable {
                            Constants.ServiceObject.options.firstOrNull { it.label == currentSelection }?.let(onSubmit)
                        }
                    } else {
                        Modifier
                    }).alpha(if (currentSelection.isNotBlank()) 1f else 0.4f),
                    text = stringResource(R.string.done),
                    fontColor = colorResource(R.color.blue),
                    fontSize = 18.sp
                )
            }

            TextFieldSearch(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                text = searchQuery,
                hint = stringResource(R.string.search),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(),
                onValueChange = { searchQuery = it }
            )

            val filteredItems = Constants.ServiceObject.options.filter { it.label.contains(searchQuery, ignoreCase = true) }

            filteredItems.forEachIndexed { index, data ->
                val isSelected = currentSelection == data.label
                //val priceText = formatCurrency(data.price, data.currencyCode)

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            currentSelection = data.label
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(vertical = 15.dp)
                            .size(30.dp),
                        painter = painterResource(data.iconRes),
                        contentDescription = stringResource(R.string.app_name)
                    )
                    TextW400(
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                            .weight(1.0f),
                        text = data.label,
                        fontColor = colorResource(R.color.baseBlack),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    /*priceText?.let {
                        TextW500(
                            text = it,
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.End
                        )
                    }*/
                    if (isSelected) {
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(R.drawable.ic_checked),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                }
                if (filteredItems.lastIndex != index) {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        color = colorResource(R.color.divider)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun DialogServicesPreview() {
    ChargebackAndroidAppTheme {
        DialogServices(
            onDismissRequest = {},
            onSubmit = {}
        )
    }
}