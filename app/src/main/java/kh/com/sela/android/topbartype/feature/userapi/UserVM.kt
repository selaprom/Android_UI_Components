package kh.com.sela.android.topbartype.feature.userapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.data.request.UserModelRequest
import kh.com.sela.android.topbartype.data.request.UserUpdateRequest
import kh.com.sela.android.topbartype.data.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.data.response.UserModelResponse
import kh.com.sela.android.topbartype.data.response.UserUpdateResponse
import kh.com.sela.android.topbartype.domain.usecase.CreateUserUseCase
import kh.com.sela.android.topbartype.domain.usecase.DeleteUserUseCase
import kh.com.sela.android.topbartype.domain.usecase.GetUserListUseCase
import kh.com.sela.android.topbartype.domain.usecase.UpdateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel


class UserVM @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    //private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {
    private val _usersUiState =
        MutableStateFlow<BaseUiState<List<UserModelResponse>>>(BaseUiState.None)
    private val _userUiState = MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)


    private val _userUpdateUiState =
        MutableStateFlow<BaseUiState<UserUpdateResponse>>(BaseUiState.None)
    private val _userDeleteUiState =
        MutableStateFlow<BaseUiState<UserModelDeleteResponse>>(BaseUiState.None)
    val usersUiState = _usersUiState.asStateFlow()
    val userUiState = _userUiState.asStateFlow()
    var userDeleteUiState = _userDeleteUiState.asStateFlow()

    val userUpdateUiState = _userUpdateUiState.asStateFlow()

    fun createUser(body: UserModelRequest) {
        viewModelScope.launch {
            createUserUseCase.invoke(body).collect {
                _userUiState.value = it
            }
        }

    }

    fun getUsers() {
        viewModelScope.launch {
            getUserListUseCase.invoke(Unit).collect {
                _usersUiState.value = it
               // println("this response=====> $it")
            }
            }
    }


    fun onDispose() {
        _usersUiState.value = BaseUiState.None
        _userUiState.value = BaseUiState.None
        _userDeleteUiState.value = BaseUiState.None
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            deleteUserUseCase.invoke(id.toString()).collect {
                _userDeleteUiState.value = it
            }
        }
    }

    fun updateUser( body: UserUpdateRequest) {
        viewModelScope.launch {
            updateUserUseCase.invoke( body).collect {
                _userUpdateUiState.value = it
            }
        }
    }

}

