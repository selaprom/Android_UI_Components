package kh.com.sela.android.topbartype.domain.usecase

import kh.com.sela.android.topbartype.domain.BaseUseCase
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.data.request.UserUpdateRequest
import kh.com.sela.android.topbartype.data.response.UserUpdateResponse
import kh.com.sela.android.topbartype.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
): BaseUseCase<UserUpdateRequest, Flow<BaseUiState<UserUpdateResponse>>>() {
    override suspend fun execute(params: UserUpdateRequest):Flow<BaseUiState<UserUpdateResponse>> {
        return flow {

            try {
                emit(BaseUiState.Loading)
                val response = repository.updateUser(id =params.id.toString() ,user = params)
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