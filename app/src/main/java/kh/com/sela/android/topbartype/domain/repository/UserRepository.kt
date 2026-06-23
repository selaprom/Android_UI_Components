package kh.com.sela.android.topbartype.domain.repository

import kh.com.sela.android.topbartype.data.request.UserModelRequest
import kh.com.sela.android.topbartype.data.request.UserUpdateRequest
import kh.com.sela.android.topbartype.data.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.data.response.UserModelResponse
import kh.com.sela.android.topbartype.data.response.UserUpdateResponse
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(): Response<List<UserModelResponse>>
    suspend fun createUser(user: UserModelRequest): Response<UserModelResponse>
    suspend fun deleteUser(id: String): Response<UserModelDeleteResponse>
    suspend fun updateUser(id: String, user: UserUpdateRequest): Response<UserUpdateResponse>

}