package com.chargeback.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargeback.app.R
import com.chargeback.app.ui.theme.graphikTrialFontFamily

@Composable
fun TextFieldMain(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    minLines: Int = 1,
    maxLines: Int = 1,
    maxLength: Int = 100,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    val wasKeyboardVisible = remember { mutableStateOf(isKeyboardVisible) }

    LaunchedEffect(isKeyboardVisible) {
        if (wasKeyboardVisible.value && !isKeyboardVisible) {
            focusManager.clearFocus(force = true)
        }
        wasKeyboardVisible.value = isKeyboardVisible
    }

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(color = colorResource(R.color.transparent))
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    onValueChange(it.take(maxLength))
                },
                visualTransformation = prefixVisualTransformation("$"),
                textStyle = TextStyle(
                    fontFamily = graphikTrialFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = colorResource(R.color.black),
                    textAlign = TextAlign.End
                ),
                cursorBrush = SolidColor(colorResource(R.color.black)),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = true,
                minLines = minLines,
                maxLines = maxLines,
                decorationBox = { inputField ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (text.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = buildString {
                                        append("$")
                                        if (hint.isNotEmpty()) {
                                            append(hint)
                                        }
                                    },
                                    style = TextStyle(
                                        fontFamily = graphikTrialFontFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        color = colorResource(R.color.black),
                                        textAlign = TextAlign.End
                                    )
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
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

private fun prefixVisualTransformation(prefix: String): VisualTransformation {
    return VisualTransformation { text ->
        val prefixedText = AnnotatedString(prefix + text.text)
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = offset + prefix.length

            override fun transformedToOriginal(offset: Int): Int =
                (offset - prefix.length).coerceAtLeast(0)
        }
        TransformedText(prefixedText, offsetMapping)
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldMainPreview() {
    var text by rememberSaveable { mutableStateOf("") }

    TextFieldMain(
        modifier = Modifier
            .padding(18.dp)
            .fillMaxWidth(),
        text = text,
        hint = stringResource(R.string.app_name),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(),
        onValueChange = {}
    )
}