package dev.medzik.android.components.color

import androidx.compose.ui.graphics.Color

fun Color.combineAlpha(alpha: Float) = copy(alpha = this.alpha * alpha)
