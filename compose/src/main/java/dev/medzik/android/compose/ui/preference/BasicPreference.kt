package dev.medzik.android.compose.ui.preference

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.compose.theme.DisabledAlpha
import dev.medzik.android.compose.theme.NormalAlpha
import dev.medzik.android.compose.theme.spacing
import dev.medzik.android.compose.ui.ButtonIconSpacer
import dev.medzik.android.compose.ui.IconBox

/**
 * Displays a basic preference entry.
 *
 * @param modifier the [Modifier] to be applied to the root element
 * @param title title of the preference entry
 * @param subtitle subtitle of the preference entry
 * @param onClick called when clicked the preference entry
 * @param leading composable to display with the preference entry
 * @param trailing composable content to display at the end of the preference entry
 * @param enabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun BasicPreference(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) {
    val alpha = if (enabled) NormalAlpha else DisabledAlpha

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .alpha(alpha)
            .padding(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leading != null) {
            leading()

            ButtonIconSpacer()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = MaterialTheme.spacing.extraSmall)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        if (trailing != null) {
            ButtonIconSpacer()

            trailing()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicPreferencePreview() {
    Column {
        BasicPreference(
            title = "Basic preference",
            onClick = {}
        )

        BasicPreference(
            title = "Basic preference",
            subtitle = "Subtitle of the basic preference",
            onClick = {}
        )

        BasicPreference(
            title = "Disabled",
            subtitle = "This preference is disabled",
            onClick = {},
            enabled = false
        )

        BasicPreference(
            title = "Settings",
            onClick = {},
            leading = {
                IconBox(imageVector = Icons.Default.Settings)
            }
        )

        BasicPreference(
            title = "Settings",
            subtitle = "Switch to settings screen",
            onClick = {},
            leading = {
                IconBox(imageVector = Icons.Default.Settings)
            },
            trailing = {
                IconBox(imageVector = Icons.Default.ArrowOutward)
            }
        )
    }
}
