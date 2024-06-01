package dev.medzik.android.components.ui.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.components.TextFieldValue
import dev.medzik.android.components.rememberMutableString

@Composable
fun PasswordAnimatedTextField(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    value: TextFieldValue,
    placeholder: String? = null,
    label: String? = null,
    disabled: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    clearButton: Boolean = true,
    leading: (@Composable RowScope.() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.Password,
            null
        )
    },
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    MaskedAnimatedTextField(
        modifier = modifier,
        fieldModifier = fieldModifier,
        boxModifier = boxModifier,
        value = value,
        placeholder = placeholder,
        label = label,
        disabled = disabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions.copy(
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        interactionSource = interactionSource,
        clearButton = clearButton,
        leading = leading,
        trailing = trailing,
        content = content
    )
}

@Preview
@Composable
fun PasswordAnimatedTextFieldPreview() {
    val password = rememberMutableString()

    MaterialTheme {
        PasswordAnimatedTextField(
            value = TextFieldValue.fromMutableState(
                state = password
            )
        )
    }
}
