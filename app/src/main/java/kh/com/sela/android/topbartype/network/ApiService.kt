package kh.com.sela.android.topbartype.network


import kh.com.sela.android.topbartype.domain.model.request.UserModelRequest
import kh.com.sela.android.topbartype.domain.model.request.UserUpdateRequest
import kh.com.sela.android.topbartype.domain.model.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.domain.model.response.UserModelResponse
import kh.com.sela.android.topbartype.domain.model.response.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("getusers")
    suspend fun getUsers(): List<UserModelResponse>

    @POST("insert")
    suspend fun createUser(@Body user: UserModelRequest): UserModelResponse

    @POST("deletuser/{id}")
    suspend fun deleteUser(@Path("id") id: String, ): UserModelDeleteResponse

    @PUT("updateuser/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: UserUpdateRequest): Response<UserUpdateResponse>

}

