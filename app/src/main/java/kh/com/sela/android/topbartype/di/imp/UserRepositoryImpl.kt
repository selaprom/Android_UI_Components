package kh.com.sela.android.topbartype.di.imp

import kh.com.sela.android.topbartype.domain.model.request.UserModelRequest
import kh.com.sela.android.topbartype.domain.model.request.UserUpdateRequest
import kh.com.sela.android.topbartype.domain.model.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.domain.model.response.UserModelResponse
import kh.com.sela.android.topbartype.domain.model.response.UserUpdateResponse
import kh.com.sela.android.topbartype.domain.repository.UserRepository
import kh.com.sela.android.topbartype.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUsers(): Response<List<UserModelResponse>> {
        return apiService.getUsers()
    }

    override suspend fun createUser(user: UserModelRequest): Response<UserModelResponse> {
        return apiService.createUser(user)
    }

    override suspend fun deleteUser(id: String): Response<UserModelDeleteResponse> {
        return apiService.deleteUser(id)
    }

    override suspend fun updateUser(
        id: String,
        user: UserUpdateRequest
    ): Response<UserUpdateResponse> {
        return apiService.updateUser(id, user)
    }

}