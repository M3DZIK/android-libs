package dev.medzik.android.compose.ui.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.medzik.android.compose.color.DisabledAlpha
import dev.medzik.android.compose.color.NormalAlpha
import dev.medzik.android.compose.color.combineAlpha
import dev.medzik.android.compose.icons.VisibilityIcon
import dev.medzik.android.compose.rememberMutable
import dev.medzik.android.compose.ui.ExpandedIfNotEmpty
import dev.medzik.android.compose.ui.ExpandedIfNotEmptyRow
import dev.medzik.android.compose.ui.IconBox

@Composable
fun AnimatedTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    placeholder: String? = null,
    label: String? = null,
    disabled: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    clearButton: Boolean = false,
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    val isError = value.error != null

    var hasFocus by rememberMutable(false)

    AnimatedTextFieldSurface(
        modifier = modifier.onFocusChanged {
            // change focus state only if the value is editable
            if (value.editable) hasFocus = it.hasFocus
        },
        isError = isError,
        isFocused = hasFocus
    ) {
        Column {
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val disabledAlphaTarget = if (disabled) DisabledAlpha else NormalAlpha
                val disabledAlphaState = animateFloatAsState(
                    disabledAlphaTarget,
                    label = "TextField alpha"
                )

                if (leading != null) {
                    Row(
                        modifier = Modifier.graphicsLayer {
                            alpha = disabledAlphaState.value
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        leading()
                    }

                    Spacer(
                        modifier = Modifier.width(16.dp)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    val focused = hasFocus || value.value.isNotEmpty()

                    TextFieldLabelLayout(
                        modifier = Modifier.heightIn(min = 50.dp),
                        expanded = focused
                    ) {
                        if (label != null) {
                            TextFieldLabel(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                                    .graphicsLayer {
                                        alpha = disabledAlphaState.value
                                    },
                                text = label,
                                expanded = focused,
                                hasFocus = hasFocus,
                                hasError = isError
                            )
                        }

                        val finalPlaceholder = placeholder ?: value.hint
                        PlainTextField(
                            modifier = fieldModifier.fillMaxWidth(),
                            boxModifier = boxModifier,
                            value = value.value,
                            onValueChange = value.onChange,
                            placeholder = if (finalPlaceholder != null) {
                                {
                                    val placeholderAlpha by animateFloatAsState(
                                        targetValue = if (focused || label == null) 1f else 0f,
                                        label = "Placeholder alpha"
                                    )

                                    Text(
                                        modifier = Modifier.alpha(placeholderAlpha),
                                        text = finalPlaceholder,
                                        maxLines = 1,
                                    )
                                }
                            } else {
                                null
                            },
                            enabled = !disabled,
                            readOnly = !value.editable,
                            textStyle = textStyle,
                            isError = isError,
                            visualTransformation = visualTransformation,
                            keyboardOptions = keyboardOptions,
                            keyboardActions = keyboardActions,
                            singleLine = singleLine,
                            maxLines = maxLines,
                            interactionSource = interactionSource
                        )
                    }

                    Column(
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        ExpandedIfNotEmpty(
                            value = value.error ?: value.valueLabel
                        ) {
                            AnimatedTextFieldBadge(
                                error = it as? String,
                                badge = it as? TextFieldValue.ValueLabel
                            )
                        }

                        if (content != null) {
                            content()
                        }
                    }
                }

                if (trailing != null) {
                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Row(
                        modifier = Modifier.graphicsLayer {
                            alpha = disabledAlphaState.value
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        trailing()
                    }
                }

                ExpandedIfNotEmptyRow(
                    value = Unit.takeIf { value.value.isNotEmpty() && hasFocus && clearButton }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )

                        VerticalDivider(
                            modifier = Modifier.height(24.dp),
                            color = LocalContentColor.current.combineAlpha(0.24f)
                        )

                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )

                        IconButton(
                            enabled = value.editable,
                            onClick = { value.onChange("") }
                        ) {
                            IconBox(imageVector = Icons.Outlined.Clear)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedTextFieldSurface(
    modifier: Modifier = Modifier,
    isError: Boolean,
    isFocused: Boolean,
    content: @Composable () -> Unit
) {
    val borderBaseColor = when {
        isError -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    val borderTargetColor = when {
        isError || isFocused -> borderBaseColor
        else -> borderBaseColor.combineAlpha(0f)
    }

    val borderColor by animateColorAsState(
        targetValue = borderTargetColor,
        label = "TextField border color"
    )

    val shape = MaterialTheme.shapes.large

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, shape),
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        content()
    }
}

@Preview
@Composable
private fun AnimatedTextFieldPreview() {
    val valueState = rememberMutable("")

    val value = TextFieldValue.fromMutableState(
        state = valueState,
        hint = "Value hint",
        error = if (valueState.value.length > 5) "Too long" else null
    )

    var visibility by rememberMutable(false)

    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AnimatedTextField(
                value = value,
                label = "Empty TextField"
            )

            AnimatedTextField(
                value = value,
                label = "Label",
                leading = {
                    IconBox(imageVector = Icons.Default.Password)
                },
                clearButton = true,
                trailing = {
                    IconButton(onClick = { visibility = !visibility }) {
                        VisibilityIcon(visibility = visibility)
                    }
                },
                visualTransformation = if (visibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                )
            )

            AnimatedTextField(
                value = TextFieldValue(
                    value = "user@example.com",
                    editable = false,
                    valueLabel = TextFieldValue.ValueLabel(
                        type = TextFieldValue.ValueLabel.Type.SUCCESS,
                        text = "Verified"
                    )
                ),
                label = "E-mail",
                leading = {
                    IconBox(imageVector = Icons.Default.AccountCircle)
                }
            )

            AnimatedTextField(
                value = TextFieldValue(
                    value = "Disabled text field",
                    editable = false,
                    valueLabel = TextFieldValue.ValueLabel(
                        type = TextFieldValue.ValueLabel.Type.WARNING,
                        text = "Some message"
                    )
                ),
                label = "Label",
                leading = {
                    IconBox(imageVector = Icons.Default.Info)
                },
                trailing = {
                    IconBox(imageVector = Icons.Default.Close)
                },
                disabled = true
            )
        }
    }
}
