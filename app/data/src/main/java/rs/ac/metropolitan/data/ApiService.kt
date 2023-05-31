package rs.ac.metropolitan.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.common.DatingItem

interface ApiService {

    @GET(Constants.DATING_URL)
    suspend fun getDatings(): List<DatingItem>

    @POST(Constants.DATING_URL)
    suspend fun addDating(@Body datingItem: DatingItem)

    @DELETE(Constants.DATING_URL+"/{id}")
    suspend fun deleteDating(@Path ("id") id: String)

}