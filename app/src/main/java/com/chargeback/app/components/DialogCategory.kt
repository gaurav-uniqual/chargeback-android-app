package com.chargeback.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.ui.theme.ChargebackAndroidAppTheme
import com.chargeback.app.ui.theme.TextW400
import com.chargeback.app.ui.theme.TextW500
import com.chargeback.app.utils.Constants
import com.chargeback.app.utils.Constants.CategoryObject.CAT_SUBSCRIPTION
import com.chargeback.app.utils.IconOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCategory(
    onDismissRequest: () -> Unit,
    onSubmit: (IconOption) -> Unit,
    selectedCategory: String = CAT_SUBSCRIPTION
) {
    var currentSelection by rememberSaveable { mutableStateOf(selectedCategory) }

    LaunchedEffect(selectedCategory) {
        currentSelection = selectedCategory
    }

    val sheetState = if (LocalInspectionMode.current) {
        rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    } else {
        rememberModalBottomSheetState(skipPartiallyExpanded = true)
    }

    ModalBottomSheet(
        modifier = Modifier
            .wrapContentHeight(),
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
                    text = stringResource(R.string.category),
                    fontColor = colorResource(R.color.baseBlack),
                    fontSize = 18.sp
                )
                val canSubmit = Constants.CategoryObject.options.any { it.label == currentSelection }
                TextW500(
                    modifier = Modifier
                        .alpha(if (canSubmit) 1f else 0.4f)
                        .clickable(
                            enabled = canSubmit,
                            onClick = {
                                Constants.CategoryObject.options.firstOrNull { it.label == currentSelection }?.let(onSubmit)
                            }
                        ),
                    text = stringResource(R.string.done),
                    fontColor = colorResource(R.color.blue),
                    fontSize = 18.sp
                )
            }

            Constants.CategoryObject.options.forEachIndexed { index, data ->
                val isSelected = currentSelection == data.label
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            currentSelection = data.label
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .padding(vertical = 15.dp)
                            .size(30.dp)
                            .background(color = colorResource(R.color.neutral100), shape = RoundedCornerShape(90.dp))
                            .padding(5.dp),
                        painter = painterResource(data.iconRes),
                        contentDescription = stringResource(R.string.app_name)
                    )
                    TextW400(
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                            .weight(1f),
                        text = data.label,
                        fontColor = colorResource(R.color.baseBlack),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    if (isSelected) {
                        Image(
                            painter = painterResource(R.drawable.ic_checked),
                            contentDescription = "checked",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                if (Constants.CategoryObject.options.lastIndex != index) {
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
private fun DialogCategoryPreview() {
    ChargebackAndroidAppTheme {
        DialogCategory(
            onDismissRequest = {},
            onSubmit = {}
        )
    }
}