package dev.medzik.android.components.color

import androidx.compose.ui.graphics.Color

/**
 * Returns a new [Color] with alpha multiplied by given [alpha] value.
 *
 * @param alpha the alpha value to multiple by.
 * @return A new [Color] with alpha multiplied by given [alpha] value.
 */
fun Color.combineAlpha(alpha: Float) = copy(alpha = this.alpha * alpha)
