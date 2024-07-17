package dev.medzik.android.compose.ui

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
import dev.medzik.android.compose.rememberMutable

/**
 * Dropdown menu for selecting value.
 *
 * @param modifier the [Modifier] to be applied to the text field
 * @param values list of values
 * @param value currently selected value
 * @param onValueChange callback to be called when a value is selected
 * @param label label for the text field
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ComboBoxDropdown(
    modifier: Modifier = Modifier,
    values: Array<T>,
    value: T,
    onValueChange: (T) -> Unit,
    getValueName: (T) -> String = { it.toString() },
    label: (@Composable () -> Unit)? = null
) {
    var expanded by rememberMutable { false }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = modifier.menuAnchor(),
            value = getValueName(value),
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
                    text = { Text(getValueName(it)) },
                    onClick = {
                        expanded = false
                        onValueChange(it)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ComboBoxDropdownPreview() {
    val values = arrayOf("A", "B", "C")

    var value by rememberMutable { values.first() }

    ComboBoxDropdown(
        values = values,
        value = value,
        onValueChange = { value = it },
        label = { Text("Example dropdown") }
    )
}
