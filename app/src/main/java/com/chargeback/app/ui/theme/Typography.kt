package com.chargeback.app.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.chargeback.app.R

@Composable
private fun WeightedText(
    weight: FontWeight,
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontFamily = graphikTrialFontFamily,
            fontWeight = weight,
            fontSize = fontSize,
            color = fontColor,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        textAlign = textAlign,
        maxLines = maxLines,
        textDecoration = textDecoration
    )
}

@Composable
fun TextW100(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Thin, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW200(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.ExtraLight, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW300(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Light, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW400(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Normal, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW500(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Medium, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW600(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.SemiBold, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW700(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Bold, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW800(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.ExtraBold, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Composable
fun TextW900(
    modifier: Modifier = Modifier,
    text: String = "",
    fontColor: Color = colorResource(R.color.black),
    fontSize: TextUnit = 12.sp,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration = TextDecoration.None
) = WeightedText(FontWeight.Black, modifier, text, fontColor, fontSize, textAlign, maxLines, textDecoration)

@Preview(showBackground = true)
@Composable
fun TypographyComponentsPreview() {
    Column {
        TextW100(text = stringResource(R.string.app_name))
        TextW200(text = stringResource(R.string.app_name))
        TextW300(text = stringResource(R.string.app_name))
        TextW400(text = stringResource(R.string.app_name))
        TextW500(text = stringResource(R.string.app_name))
        TextW600(text = stringResource(R.string.app_name))
        TextW700(text = stringResource(R.string.app_name))
        TextW800(text = stringResource(R.string.app_name))
        TextW900(text = stringResource(R.string.app_name))
    }
}