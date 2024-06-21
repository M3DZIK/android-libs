package dev.medzik.android.compose.ui.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberBottomSheetState(
    sheetState: SheetState = SheetState(
        skipPartiallyExpanded = true,
        density = LocalDensity.current
    )
) = remember { BottomSheetState(sheetState) }

/**
 * A visibility controller for a bottom sheet
 */
@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetState(internal val sheetState: SheetState) {
    internal var visible by mutableStateOf(false)

    /**
     * Hide the bottom sheet.
     */
    suspend fun hide() {
        sheetState.hide()
        visible = false
    }

    /**
     * Show the bottom sheet.
     */
    fun show() {
        visible = true
    }
}
