package rs.ac.metropolitan.dz_12

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.common.DatingItem
import rs.ac.metropolitan.dz_12.navigation.NavigationRoutes
import rs.ac.metropolitan.repository.Repository

class AppViewModel : ViewModel(){

    lateinit var navController: NavHostController
    val repository = Repository()
    val granted = mutableStateOf(false)

    private val _datings = MutableLiveData<List<DatingItem>>()
    val datings: LiveData<List<DatingItem>>
        get() = _datings

    fun loadDatings(){
        GlobalScope.launch {
            repository.loadDatings()
            MainScope().launch {
                repository.datingFlow.collect{
                    _datings.value = it
                }
            }
        }
    }

    fun getDating(id: String): DatingItem?{
        return _datings.value?.find { it.id == id }
    }

    fun deleteDating(id: String){
        GlobalScope.launch {
            repository.deleteDating(id)
        }
        goBack()
    }

    fun addDating(dating: DatingItem){
        GlobalScope.launch {
            repository.addDating(dating)
        }
    }

    fun navToDatingDetails(id: String){
        navController.navigate(NavigationRoutes.DatingDetailScreen.createRoute(id))
    }

    fun navToNewDating(){
        navController.navigate(NavigationRoutes.NewDating.route)
    }

    fun goBack(){
        navController.popBackStack()
    }
}