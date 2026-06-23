package kh.com.sela.android.topbartype.network


import kh.com.sela.android.topbartype.data.request.UserModelRequest
import kh.com.sela.android.topbartype.data.request.UserUpdateRequest
import kh.com.sela.android.topbartype.data.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.data.response.UserModelResponse
import kh.com.sela.android.topbartype.data.response.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("getusers")
    suspend fun getUsers(): Response<List<UserModelResponse>>

    @POST("insert")
    suspend fun createUser(@Body user: UserModelRequest): Response<UserModelResponse>

    @POST("deletuser/{id}")
    suspend fun deleteUser(@Path("id") id: String, ): Response<UserModelDeleteResponse>

    @PUT("updateuser/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: UserUpdateRequest): Response<UserUpdateResponse>

}

