package dev.medzik.android.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

const val DOT_CHAR = '•'

/**
 * Generates a dotted string representing a password.
 *
 * @param dotChar the character to use as a dot
 * @param dottedPasswordLength the length of the dotted string
 * @return the string of a dotted characters representing the password
 */
fun dottedPassword(
    dotChar: Char = DOT_CHAR,
    dottedPasswordLength: Int = 8
): String {
    return buildString {
        repeat(dottedPasswordLength) {
            append(dotChar)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DottedPasswordPreview() {
    Text(dottedPassword())
}

/**
 * Colorizes the password string based on the content color.
 *
 * @param password the password string to color
 * @param contentColor the color to be used as the base for letters
 * @return the colorized password string
 */
fun colorizePassword(
    password: String,
    contentColor: Color
) = colorizePassword(
    password = password,
    digitColor = colorizePasswordDigitColor(contentColor),
    symbolColor = colorizePasswordSymbolColor(contentColor),
)

/**
 * Determines the color for digits in the password based on the [contentColor].
 */
fun colorizePasswordDigitColor(
    contentColor: Color,
) = colorizePasswordDigitColor(onDark = contentColor.luminance() > 0.5f)

private fun colorizePasswordDigitColor(onDark: Boolean) = run {
    val saturation: Float
    val lightness: Float

    if (onDark) {
        saturation = 0.52f
        lightness = 0.56f
    } else {
        saturation = 0.72f
        lightness = 0.62f
    }

    Color.hsl(
        hue = 210f,
        saturation = saturation,
        lightness = lightness,
    )
}

/**
 * Determines the color for symbols in the password based on the [contentColor].
 */
fun colorizePasswordSymbolColor(
    contentColor: Color,
) = colorizePasswordSymbolColor(onDark = contentColor.luminance() > 0.5f)

private fun colorizePasswordSymbolColor(onDark: Boolean) = run {
    val saturation: Float
    val lightness: Float

    if (onDark) {
        saturation = 0.52f
        lightness = 0.56f
    } else {
        saturation = 0.72f
        lightness = 0.62f
    }

    Color.hsl(
        hue = 0f,
        saturation = saturation,
        lightness = lightness,
    )
}

private fun colorizePassword(
    password: String,
    digitColor: Color = Color.Blue,
    symbolColor: Color = Color.Red
) = buildAnnotatedString {
    password.forEach { char ->
        when {
            char.isSurrogate() -> append(char)
            char.isLetter() -> append(char)

            char.isDigit() -> {
                withStyle(
                    style = SpanStyle(
                        color = digitColor,
                    ),
                ) {
                    append(char)
                }
            }

            else -> {
                withStyle(
                    style = SpanStyle(
                        color = symbolColor,
                    )
                ) {
                    append(char)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColorizePasswordPreview() {
    Text(colorizePassword("Qwerty123!", contentColor = MaterialTheme.colorScheme.onBackground))
}

@Composable
fun colorizePasswordTransformation(): VisualTransformation {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground

    return VisualTransformation { text ->
        TransformedText(
            text = colorizePassword(text.text, contentColor = onBackgroundColor),
            OffsetMapping.Identity
        )
    }
}
