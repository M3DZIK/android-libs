package dev.medzik.android.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview

/**
 * Dropdown menu for selecting an enum value.
 *
 * @param modifier The modifier to apply to the composable.
 * @param values The list of items to display in the dropdown menu.
 * @param value The currently selected item.
 * @param onValueChange A callback invoked when the selected item changes.
 * @param label The content of the text field.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> ComboBoxDropdown(
    modifier: Modifier = Modifier,
    values: Array<T>,
    value: T,
    onValueChange: (T) -> Unit,
    label: (@Composable () -> Unit)? = null
) {
    var expanded by rememberMutableBoolean(false)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(),
            value = value.name,
            onValueChange = {},
            singleLine = true,
            label = label,
            readOnly = true,
            trailingIcon = {
                val iconRotation by animateFloatAsState(
                    targetValue = if (expanded) 180f else 0f,
                    label = ""
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    modifier = Modifier.rotate(iconRotation),
                    contentDescription = null
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            values.forEach {
                DropdownMenuItem(
                    text = { Text(it.name) },
                    onClick = {
                        expanded = false
                        onValueChange(it)
                    }
                )
            }
        }
    }
}

private enum class ExampleEnum {
    FIRST
}

@Preview(showBackground = true)
@Composable
fun ComboBoxDropdownPreview() {
    ComboBoxDropdown(
        values = ExampleEnum.entries.toTypedArray(),
        value = ExampleEnum.FIRST,
        onValueChange = {},
        label = { Text("Example dropdown") }
    )
}
