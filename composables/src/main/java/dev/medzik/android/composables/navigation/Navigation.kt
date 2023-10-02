package dev.medzik.android.composables.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

/** Gets the value of argument from navigation controller */
fun NavController.getString(argument: NavArgument): String? {
    return currentBackStackEntry?.arguments?.getString(argument.name.lowercase())
}

/**
 * Navigates to the specified [NavScreen] with the provided [NavArgument] values.
 * @param screen the target screen to navigate to
 * @param args a list of argument pairs in the form of [NavArgument] and [String] values
 * @param disableBack determines whether to disable the "back" navigation from this screen
 * @param builderOptions a function that allows customization of custom navigation options
 */
fun NavController.navigate(
    screen: NavScreen,
    args: Array<Pair<NavArgument, String>>? = null,
    disableBack: Boolean = false,
    builderOptions: (NavOptionsBuilder.() -> Unit)? = null
) {
    val route = if (args != null) screen.fill(*args) else screen.fill()

    navigate(
        route = route,
        builder = {
            // Disable back navigation
            if (disableBack) popUpTo(graph.startDestinationId) { inclusive = true }

            if (builderOptions != null) builderOptions()
        }
    )
}
