package com.chargeback.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.ui.theme.graphikTrialFontFamily

@Composable
fun TextFieldSearch(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = colorResource(R.color.transparent),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = colorResource(R.color.neutral300),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    onValueChange(it)
                },
                textStyle = TextStyle(
                    fontFamily = graphikTrialFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = colorResource(R.color.baseBlack)
                ),
                cursorBrush = SolidColor(colorResource(R.color.baseBlack)),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                decorationBox = { inputField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.padding(top = 11.dp, bottom = 11.dp, start = 16.dp, end = 10.dp),
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = stringResource(R.string.app_name)
                        )

                        if (text.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = hint,
                                    style = TextStyle(
                                        fontFamily = graphikTrialFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = colorResource(R.color.neutral500)
                                    )
                                )
                                inputField()
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                inputField()
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldSearchPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    TextFieldSearch(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth(),
        text = text,
        hint = stringResource(R.string.search),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(),
        onValueChange = {}
    )
}