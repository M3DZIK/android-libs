package dev.medzik.android.compose

import androidx.annotation.UiThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import dev.medzik.common.io.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Binds an [IO] block to the [LifecycleOwner]'s [Lifecycle].
 *
 * The block will be executed when the lifecycle state is at least [lifecycleState] and
 * will be cancelled when the lifecycle state falls below [lifecycleState].
 *
 * @param lifecycleState The minimum lifecycle state required for the block to be executed.
 * @param block The [IO] block to be executed.
 * @return A function that removes the observer and cancels the block execution.
 */
@UiThread
fun <T> LifecycleOwner.bind(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    block: IO<T>
): () -> Unit {
    var wasActive = false
    var job: Job? = null

    val eventObserver = LifecycleEventObserver { source, _ ->
        val isActive = source.lifecycle.currentState >= lifecycleState
        if (isActive) {
            if (!wasActive) {
                job?.cancel()
                job = lifecycleScope.launch {
                    block()
                }
            }
        } else {
            job?.cancel()
            job = null
        }

        wasActive = isActive
    }

    val remover = {
        lifecycle.removeObserver(eventObserver)
        job?.cancel()
        job = null
    }

    lifecycle.addObserver(eventObserver)

    return remover
}
