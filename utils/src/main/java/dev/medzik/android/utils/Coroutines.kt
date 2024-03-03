package dev.medzik.android.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Runs the code block on the main (UI) thread using Kotlin Coroutines.
 *
 * @param block The code block which will be executed on the UI thread.
 */
fun runOnUiThread(block: suspend () -> Unit) = MainScope().launch(Dispatchers.Main) { block() }

/**
 * Runs the code block on the IO thread using Kotlin Coroutines.
 *
 * @param block The code block which will be executed on the IO thread.
 */
fun runOnIOThread(block: suspend () -> Unit) = MainScope().launch(Dispatchers.IO) { block() }
