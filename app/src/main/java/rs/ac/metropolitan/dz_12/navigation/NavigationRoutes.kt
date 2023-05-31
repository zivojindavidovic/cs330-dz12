package rs.ac.metropolitan.dz_12.navigation

sealed class NavigationRoutes(val route: String) {
    object Home: NavigationRoutes(route = "home")
    object NewDating: NavigationRoutes(route = "new")
    object DatingDetailScreen: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
}