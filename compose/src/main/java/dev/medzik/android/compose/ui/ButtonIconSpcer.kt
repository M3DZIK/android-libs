package dev.medzik.android.compose.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.medzik.android.compose.theme.spacing

@Composable
fun ButtonIconSpacer() {
    Spacer(
        modifier = Modifier.width(MaterialTheme.spacing.buttonIcon)
    )
}
