package dev.medzik.android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

/**
 * A back arrow icon in the app bar. When clicked,
 * it navigates back in the navigation stack using the provided [NavController].
 *
 * @param navController the [NavController] instance for navigating back
 */
@Composable
fun TopAppBarBackIcon(navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null
        )
    }
}
