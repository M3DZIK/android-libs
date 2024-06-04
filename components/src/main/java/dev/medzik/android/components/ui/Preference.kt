package dev.medzik.android.components.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.medzik.android.components.rememberMutable
import dev.medzik.android.components.rememberMutableBoolean

/**
 * Title for a group of preferences.
 *
 * @param title title of the group
 * @param modifier the [Modifier] to be applied to the title
 */
@Composable
fun PreferenceGroupTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    GroupTitle(
        text = title,
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

/**
 * Displays a preference entry.
 *
 * @param modifier the [Modifier] to be applied to the preference entry
 * @param title title of the preference entry
 * @param description optional description of the preference entry
 * @param content optional content to display within the preference entry
 * @param icon optional icon to display with the preference entry
 * @param trailingContent optional composable content to display at the end of the preference entry
 * @param onClick called when clicked the preference entry
 * @param isEnabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun PreferenceEntry(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    content: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
            .alpha(if (isEnabled) 1f else 0.5f)
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
    ) {
        if (icon != null) {
            Box(
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                icon()
            }

            Spacer(Modifier.width(12.dp))
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 50.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            content?.invoke()
        }

        if (trailingContent != null) {
            Spacer(Modifier.width(12.dp))

            trailingContent()
        }
    }
}

/**
 * Displays a switcher preference.
 *
 * @param modifier the [Modifier] to be applied to the switcher preference
 * @param title title of the switcher preference
 * @param description optional description of the switcher preference
 * @param icon optional icon to display with the switcher preference
 * @param checked current state of the switch (checked - true or unchecked - false)
 * @param onCheckedChange called when the state of the switch is changed
 * @param isEnabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun SwitcherPreference(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    icon: (@Composable () -> Unit)? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isEnabled: Boolean = true
) {
    PreferenceEntry(
        modifier = modifier,
        title = title,
        description = description,
        icon = icon,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        },
        onClick = { onCheckedChange(!checked) },
        isEnabled = isEnabled
    )
}

/**
 * Displays a property preference.
 *
 * @param modifier The modifier for customizing the appearance and behavior of the property preference.
 * @param title The title of the property preference.
 * @param description An optional description of the property preference.
 * @param icon An optional icon to display with the property preference.
 * @param currentValue The current value of the property.
 * @param onClick called when clicked the property preference
 * @param isEnabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun PropertyPreference(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    icon: (@Composable () -> Unit)? = null,
    currentValue: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    PreferenceEntry(
        modifier = modifier,
        title = title,
        description = description,
        icon = icon,
        trailingContent = {
            Text(
                text = currentValue,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        },
        onClick = { onClick() },
        isEnabled = isEnabled
    )
}

@Preview(showBackground = true)
@Composable
fun PreferencesPreview() {
    Column {
        PreferenceGroupTitle(
            title = "First Group"
        )

        var firstChecked by rememberMutableBoolean(true)
        SwitcherPreference(
            title = "First Switcher title",
            checked = firstChecked,
            onCheckedChange = { firstChecked = !firstChecked }
        )

        var secondChecked by rememberMutableBoolean(false)
        SwitcherPreference(
            title = "Second Switcher title",
            description = "Second Switcher description",
            checked = secondChecked,
            onCheckedChange = { secondChecked = !secondChecked }
        )

        PreferenceGroupTitle(
            title = "Second Group"
        )

        val propertyItems = listOf("First", "Second", "Third")
        var currentItem by rememberMutable(propertyItems[0])
        val state = rememberDialogState()

        PropertyPreference(
            title = "Property title",
            icon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            currentValue = currentItem,
            onClick = { state.show() }
        )

        PickerDialog(
            state,
            title = "Picker Dialog",
            items = propertyItems,
            onSelected = {
                currentItem = it
            }
        ) {
            Text(
                text = it,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            )
        }
    }
}
