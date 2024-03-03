package dev.medzik.android.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/** Creates a mutable state of [initialValue]. */
@Composable
fun <T> rememberMutable(initialValue: T) = remember { mutableStateOf(initialValue) }

/** Creates a mutable string state of [initialValue]. */
@Composable
fun rememberMutableString(initialValue: String = "") = rememberMutable(initialValue)

/** Creates a mutable boolean state of [initialValue]. */
@Composable
fun rememberMutableBoolean(initialValue: Boolean = false) = rememberMutable(initialValue)
