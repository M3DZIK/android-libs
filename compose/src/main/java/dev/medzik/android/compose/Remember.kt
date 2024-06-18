package dev.medzik.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

/**
 * Remember mutable state of the [initialValue].
 */
@Composable
fun <T> rememberMutable(initialValue: T) = remember { mutableStateOf(initialValue) }

/**
 * Remember saveable mutable state of the [initialValue].
 *
 * @see rememberSaveable
 */
@Composable
fun <T> rememberSaveableMutable(initialValue: T) = rememberSaveable { mutableStateOf(initialValue) }
