package dev.medzik.android.composables.navigation

/**
 * A navigation screen interface that represents a destination within a navigation graph.
 * @property args An optional array of [NavArgument] objects representing the arguments required for this screen.
 */
interface NavScreen {
    val name: String
    val args: Array<NavArgument>?

    /**
     * Returns the route destination for the screen without filling arguments.
     * @return The route destination as a string.
     */
    fun getRoute(): String {
        return if (args != null) {
            "${name.lowercase()}/${args!!.joinToString("/") { "{${it.name.lowercase()}}" }}"
        } else name.lowercase() // if no arguments, return route without arguments
    }

    /**
     * Returns the route destination for the screen with filled arguments.
     * @param args Pairs of [NavArgument] and their corresponding argument values.
     * @return The route destination as a string with filled arguments.
     * @throws IllegalArgumentException if the number of provided arguments does not match the expected count.
     */
    fun fill(vararg args: Pair<NavArgument, String>): String {
        if (args.size != (this.args?.size ?: 0))
            throw IllegalArgumentException("Invalid number of arguments. Expected ${this.args?.size}, got ${args.size}")

        var route = getRoute()
        for (arg in args)
            route = route.replace("{${arg.first.name.lowercase()}}", arg.second)

        return route
    }
}
