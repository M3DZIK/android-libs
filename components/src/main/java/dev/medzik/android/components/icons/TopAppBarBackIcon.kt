package dev.medzik.android.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.medzik.android.components.ui.IconBox

/**
 * A back arrow icon in the app bar. When clicked,
 * it navigates back in the navigation stack using the provided [NavController].
 *
 * @param navController the [NavController] instance for navigating back
 */
@Composable
fun TopAppBarBackIcon(navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        IconBox(imageVector = Icons.AutoMirrored.Filled.ArrowBack)
    }
}
