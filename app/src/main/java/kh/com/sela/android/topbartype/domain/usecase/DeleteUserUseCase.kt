package kh.com.sela.android.topbartype.domain.usecase

import kh.com.sela.android.topbartype.domain.BaseUseCase
import kh.com.sela.android.topbartype.domain.model.base.BaseUiState
import kh.com.sela.android.topbartype.domain.model.request.UserModelRequest
import kh.com.sela.android.topbartype.domain.model.request.UserUpdateRequest
import kh.com.sela.android.topbartype.domain.model.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.domain.model.response.UserModelResponse
import kh.com.sela.android.topbartype.domain.model.response.UserUpdateResponse
import kh.com.sela.android.topbartype.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase<String, Flow<BaseUiState<UserModelDeleteResponse>>>() {
    override suspend fun execute(params: String):Flow<BaseUiState<UserModelDeleteResponse>> {
        return flow {

            try {
                emit(BaseUiState.Loading)
                val response = repository.deleteUser(params)
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        emit(BaseUiState.Success(body))
                    }
                } else {
                    emit(BaseUiState.Error(response.code(), response.message()))
                }

            } catch (e: Exception) {
                emit(BaseUiState.ErrorException(e.message.toString()))
            }
        }
    }

}