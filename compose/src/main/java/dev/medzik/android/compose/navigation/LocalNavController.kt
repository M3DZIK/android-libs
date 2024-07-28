package dev.medzik.android.compose.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/**
 * A CompositionLocal that provides a [NavController] instance to composables
 * within its scope. This allows child composables to accession utilize the
 * NavController for navigation purposes.
 *
 * To use this CompositionLocal, you must first provide a NavController instance
 * using the [NavigationProvider] composable.
 *
 * @sample NavigationProviderExample
 */
val LocalNavController = staticCompositionLocalOf<NavController> {
    error("CompositionLocal LocalNavController not present")
}

/**
 * Provides a [NavController] instance to child composables via [LocalNavController].
 *
 * @param navController The NavController instance to provide.
 * @param content The composable content that will have access to the NavController.
 *
 * @sample NavigationProviderExample
 */
@Composable
fun NavigationProvider(navController: NavController, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalNavController provides navController,
        content = content
    )
}

@Preview
@Composable
private fun NavigationProviderExample() {
    @Composable
    fun HomeScreen() {
        val navController = LocalNavController.current
        Button(
            onClick = { navController.navigate("detail") }
        ) {
            Text("Test")
        }
    }

    val navController = rememberNavController()

    NavigationProvider(navController) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen()
            }

            composable("detail") {
                // ...
            }
        }
    }
}
