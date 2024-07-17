package dev.medzik.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * Remembers the value produced by [calculation] and returns it as a [MutableState].
 */
@Composable
inline fun <T> rememberMutable(
    crossinline calculation: @DisallowComposableCalls () -> T
): MutableState<T> = remember {
    mutableStateOf(calculation())
}
