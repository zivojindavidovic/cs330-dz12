package rs.ac.metropolitan.dz_12.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import rs.ac.metropolitan.dz_12.AppViewModel
import rs.ac.metropolitan.dz_12.view.DatingDetailScreen
import rs.ac.metropolitan.dz_12.view.HomeScreen
import rs.ac.metropolitan.dz_12.view.NewDatingScreen

@Composable
fun NavSetup(navController: NavHostController){
    val vm: AppViewModel = viewModel()
    val paddingValues = PaddingValues()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route){
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(vm)
        }
        composable(route = NavigationRoutes.DatingDetailScreen.route){navBackStackEntry->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                DatingDetailScreen(vm, elementId, paddingValues)
            }else{
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
        composable(route = NavigationRoutes.NewDating.route){
            NewDatingScreen(vm = vm, paddingValues = paddingValues)
        }
    }
}