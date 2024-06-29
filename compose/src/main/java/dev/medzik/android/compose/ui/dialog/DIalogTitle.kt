package dev.medzik.android.compose.ui.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.medzik.android.compose.theme.regularHorizontalPadding
import dev.medzik.android.compose.theme.spacing

@Composable
fun DialogTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier =  Modifier
            .regularHorizontalPadding()
            .padding(bottom = MaterialTheme.spacing.dialogHalf)
    )
}
