package rs.ac.metropolitan.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.common.DatingItem
import rs.ac.metropolitan.data.ApiService
import rs.ac.metropolitan.data.RetrofitHelper

class Repository {
    var datingFlow: Flow<List<DatingItem>> = flowOf(listOf())

    suspend fun loadDatings(){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.getDatings()

        result?.let {
            datingFlow = flowOf(result)
        }
    }

    suspend fun addDating(datingItem: DatingItem){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.addDating(datingItem);
    }

    suspend fun deleteDating(id: String){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.deleteDating(id);
    }
}