package com.chargeback.app.screens

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.components.DialogCategory
import com.chargeback.app.components.DialogFrequency
import com.chargeback.app.components.DialogServices
import com.chargeback.app.ui.theme.ChargebackAndroidAppTheme
import com.chargeback.app.ui.theme.TextW400
import com.chargeback.app.ui.theme.TextW500
import com.chargeback.app.utils.Constants
import com.chargeback.app.utils.formatCurrency
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun SubscriptionScreen() {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    //var amountTxt by rememberSaveable { mutableStateOf("") }

    var isOpenServiceDialog by remember { mutableStateOf(false) }
    var selectedService by rememberSaveable { mutableStateOf("") }

    var isOpenCategoryDialog by remember { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf(Constants.CategoryObject.CAT_SUBSCRIPTION) }

    var isOpenFrequencyDialog by remember { mutableStateOf(false) }
    var selectedFrequency by rememberSaveable { mutableStateOf(Constants.FrequencyObject.WEEKLY) }

    val dateFormatter = remember { SimpleDateFormat("MMM dd, yyyy", Locale.US) }
    var selectedDate by rememberSaveable { mutableStateOf(dateFormatter.format(Calendar.getInstance().time)) }

    val serviceOption = Constants.ServiceObject.optionFor(selectedService)
    val serviceIconRes = serviceOption?.iconRes ?: R.drawable.ic_add
    val serviceDisplayText = selectedService.ifBlank {
        stringResource(R.string.choose_a_service)
    }
    val servicePriceText = serviceOption?.let {
        formatCurrency(it.price, it.currencyCode)
    } ?: stringResource(R.string.o_dollar)

    val canSave = selectedService.isNotBlank() &&
            selectedCategory.isNotBlank() &&
            //amountTxt.isNotBlank() &&
            selectedFrequency.isNotBlank()

    Column(
        modifier = Modifier
            .background(color = colorResource(R.color.backgroundPage))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(44.dp),
                painter = painterResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.app_name)
            )
            TextW500(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f),
                text = stringResource(R.string.create_subscription),
                fontColor = colorResource(R.color.baseBlack),
                fontSize = 18.sp
            )
            TextW500(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = canSave,
                    onClick = {
                        if (canSave) {
                            Toast.makeText(context, "Item Saved", Toast.LENGTH_LONG).show()
                        }
                    }
                ),
                text = stringResource(R.string.save),
                fontColor = if (canSave) {
                    colorResource(R.color.blue)
                } else {
                    colorResource(R.color.neutral400)
                },
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 6.dp)
                .background(color = colorResource(R.color.white), shape = RoundedCornerShape(16.dp))
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            isOpenServiceDialog = true
                        }),
                painter = painterResource(serviceIconRes),
                contentDescription = stringResource(R.string.app_name)
            )
            Column(
                modifier = Modifier.padding(start = 14.dp, end = 20.dp)
            ) {
                TextW400(
                    modifier = Modifier.fillMaxWidth(),
                    text = serviceDisplayText,
                    fontColor = if (selectedService.isEmpty()) {
                        colorResource(R.color.neutral400)
                    } else {
                        colorResource(R.color.baseBlack)
                    },
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                )
                TextW400(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .fillMaxWidth(),
                    text = servicePriceText,
                    fontColor = colorResource(R.color.neutral80),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .background(color = colorResource(R.color.white), shape = RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.name),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    isOpenServiceDialog = true
                                }),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextW400(
                            modifier = Modifier,
                            text = serviceDisplayText,
                            fontColor = if (selectedService.isEmpty()) {
                                colorResource(R.color.neutral400)
                            } else {
                                colorResource(R.color.baseBlack)
                            },
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(R.drawable.ic_up_down),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = colorResource(R.color.divider))
                Row(
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.amount),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextW400(
                            modifier = Modifier.weight(1f),
                            text = servicePriceText,
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.End,
                            textDecoration = TextDecoration.Underline
                        )
                        /*TextFieldMain(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = amountTxt,
                            hint = stringResource(R.string._0),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(),
                            onValueChange = {
                                amountTxt = it
                            }
                        )*/
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = colorResource(R.color.divider))
                Row(
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.category),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    isOpenCategoryDialog = true
                                }
                            ),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(
                                Constants.CategoryObject.iconFor(selectedCategory) ?: R.drawable.ic_refresh
                            ),
                            contentDescription = stringResource(R.string.app_name)
                        )
                        TextW400(
                            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                            text = selectedCategory,
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(R.drawable.ic_up_down),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .background(color = colorResource(R.color.white), shape = RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.start_date),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextW400(
                            modifier = Modifier
                                .background(color = colorResource(R.color.neutral100), shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        val calendar = Calendar.getInstance().apply {
                                            dateFormatter.parse(selectedDate)?.let { time = it }
                                        }
                                        DatePickerDialog(
                                            context,
                                            { _, year, month, dayOfMonth ->
                                                Calendar.getInstance().apply {
                                                    set(year, month, dayOfMonth)
                                                    selectedDate = dateFormatter.format(time)
                                                }
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    }
                                ),
                            text = selectedDate,
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = colorResource(R.color.divider))
                Row(
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.frequency),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextW400(
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    isOpenFrequencyDialog = true
                                }),
                            text = when (selectedFrequency) {
                                Constants.FrequencyObject.WEEKLY -> {
                                    stringResource(R.string.weekly)
                                }

                                Constants.FrequencyObject.MONTHLY -> {
                                    stringResource(R.string.monthly)
                                }

                                Constants.FrequencyObject.ANNUALLY -> {
                                    stringResource(R.string.annually)
                                }

                                else -> {
                                    selectedFrequency
                                }
                            },
                            fontColor = colorResource(R.color.baseBlack),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                        Image(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(R.drawable.ic_up_down),
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp), color = colorResource(R.color.divider))
                Row(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextW400(
                        modifier = Modifier,
                        text = stringResource(R.string.active),
                        fontColor = colorResource(R.color.neutral500),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var checked by remember { mutableStateOf(true) }

                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colorResource(R.color.white),
                                checkedTrackColor = colorResource(R.color.blue),
                                uncheckedThumbColor = colorResource(R.color.white),
                                uncheckedTrackColor = colorResource(R.color.neutral200),
                            )
                        )
                    }
                }
            }
        }

        if (isOpenServiceDialog) {
            DialogServices(
                onDismissRequest = {
                    isOpenServiceDialog = false
                },
                onSubmit = {
                    isOpenServiceDialog = false
                    selectedService = it.label
                    /*it.price?.let { price ->
                        amountTxt = String.format(Locale.US, "%.2f", price)
                    }*/
                },
                selectedService = selectedService
            )
        }

        if (isOpenCategoryDialog) {
            DialogCategory(
                onDismissRequest = {
                    isOpenCategoryDialog = false
                },
                onSubmit = {
                    isOpenCategoryDialog = false
                    selectedCategory = it.label
                },
                selectedCategory = selectedCategory
            )
        }

        if (isOpenFrequencyDialog) {
            DialogFrequency(
                onDismissRequest = {
                    isOpenFrequencyDialog = false
                },
                onSubmit = {
                    isOpenFrequencyDialog = false
                    selectedFrequency = it
                },
                selectedFrequencyMain = selectedFrequency
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionScreenPreview() {
    ChargebackAndroidAppTheme {
        SubscriptionScreen()
    }
}