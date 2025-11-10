package com.chargeback.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.ui.theme.ChargebackAndroidAppTheme
import com.chargeback.app.ui.theme.TextW400
import com.chargeback.app.ui.theme.TextW500
import com.chargeback.app.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogFrequency(
    onDismissRequest: () -> Unit,
    onSubmit: (String) -> Unit,
    selectedFrequencyMain: String = Constants.FrequencyObject.WEEKLY
) {
    val sheetState = if (LocalInspectionMode.current) {
        rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    } else {
        rememberModalBottomSheetState(skipPartiallyExpanded = true)
    }

    var selectedFrequency by rememberSaveable { mutableStateOf(selectedFrequencyMain) }

    LaunchedEffect(selectedFrequencyMain) {
        selectedFrequency = selectedFrequencyMain
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = colorResource(R.color.neutral200)
            )
        },
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        containerColor = colorResource(R.color.white),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
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
                        text = stringResource(R.string.frequency),
                        fontColor = colorResource(R.color.baseBlack),
                        fontSize = 18.sp
                    )
                    TextW500(
                        modifier = Modifier.clickable(
                            onClick = {
                                onSubmit(selectedFrequency)
                            }
                        ),
                        text = stringResource(R.string.done),
                        fontColor = colorResource(R.color.blue),
                        fontSize = 18.sp
                    )
                }

                Constants.FrequencyObject.options.forEachIndexed { index, value ->
                    Row(
                        modifier = Modifier.padding(top = if (index == 0) 20.dp else 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextW400(
                            modifier = Modifier
                                .padding(vertical = 19.dp, horizontal = 16.dp)
                                .weight(1f)
                                .clickable(onClick = {
                                    selectedFrequency = value
                                }),
                            text = when (value) {
                                Constants.FrequencyObject.WEEKLY -> stringResource(R.string.weekly)
                                Constants.FrequencyObject.MONTHLY -> stringResource(R.string.monthly)
                                Constants.FrequencyObject.ANNUALLY -> stringResource(R.string.annually)
                                else -> value
                            },
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                        if (selectedFrequency == value) {
                            Image(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(18.dp),
                                painter = painterResource(R.drawable.ic_checked),
                                contentDescription = stringResource(R.string.app_name)
                            )
                        }
                    }
                    if (index != Constants.FrequencyObject.options.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            thickness = 1.dp,
                            color = colorResource(R.color.divider)
                        )
                    }
                }
            }
        }
    )
}

@Preview(device = Devices.NEXUS_5, showBackground = true)
@Composable
fun DialogFrequencyPreview() {
    ChargebackAndroidAppTheme {
        DialogFrequency(
            onDismissRequest = {},
            onSubmit = {}
        )
    }
}