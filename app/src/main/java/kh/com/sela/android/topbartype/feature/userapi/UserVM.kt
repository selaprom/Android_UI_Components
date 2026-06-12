package kh.com.sela.android.topbartype.feature.userapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.sela.android.topbartype.model.base.BaseUiState
import kh.com.sela.android.topbartype.model.request.UserModelRequest
import kh.com.sela.android.topbartype.model.request.UserUpdateRequest
import kh.com.sela.android.topbartype.model.response.UserModelDeleteResponse
import kh.com.sela.android.topbartype.model.response.UserModelResponse
import kh.com.sela.android.topbartype.model.response.UserUpdateResponse
import kh.com.sela.android.topbartype.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserVM : ViewModel(){
    private val _usersUiState = MutableStateFlow<BaseUiState<List<UserModelResponse>>>(BaseUiState.None)
    private val _userUiState = MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)


    private val _userUpdateUiState = MutableStateFlow<BaseUiState<UserUpdateResponse>>(BaseUiState.None)
    private val _userDeleteUiState = MutableStateFlow<BaseUiState<UserModelDeleteResponse>>(BaseUiState.None)
    val usersUiState = _usersUiState.asStateFlow()
    val userUiState = _userUiState.asStateFlow()
    var userDeleteUiState = _userDeleteUiState.asStateFlow()

    val userUpdateUiState = _userUpdateUiState.asStateFlow()

    fun createUser(body: UserModelRequest){
        viewModelScope.launch {
            try {
                _userUiState.value = BaseUiState.Loading
                val response = RetrofitClient.apiService.createUser(body)
                _userUiState.value = BaseUiState.Success(response)
                println("this response=====> $response")

            }catch (e: Exception){
                println("error====> $e")
                _userUiState.value = BaseUiState.Error(500,e.message.toString())
            }
        }

    }

    fun getUsers(){
        viewModelScope.launch {
            _usersUiState.value = BaseUiState.Loading
            try {
                val response = RetrofitClient.apiService.getUsers()
                _usersUiState.value = BaseUiState.Success(response)
                println("response $response")

    }catch (e: Exception){
                _usersUiState.value = BaseUiState.Error(500,e.message.toString())
            }
        }
    }



    fun onDispose(){
        _usersUiState.value = BaseUiState.None
        _userUiState.value = BaseUiState.None
        _userDeleteUiState.value = BaseUiState.None
    }

        fun deleteUser(id: Int) {
            viewModelScope.launch {
                try {
                    _userDeleteUiState.value = BaseUiState.Loading
                    val response = RetrofitClient.apiService.deleteUser(id.toString())
                    _userDeleteUiState.value = BaseUiState.Success(response)
                    println("response $response")

                }catch (e: Exception){
                    println("error====> $e")
                    _userDeleteUiState.value = BaseUiState.Error(500,e.message.toString())
                }
            }
        }

    fun updateUser(id: String, body: UserUpdateRequest){
        viewModelScope.launch {
            try {
                _userUiState.value = BaseUiState.Loading
                val response = RetrofitClient.apiService.updateUser(id, body)
                if (response.isSuccessful){
                    _userUpdateUiState.value = BaseUiState.Success(response.body()!!)
                }else{
                    _userUpdateUiState.value = BaseUiState.Error(response.code(),response.message())
                }

        }catch (e: Exception){
                _userUpdateUiState.value = BaseUiState.Error(500, e.message.toString())
            }
        }
    }

    }

