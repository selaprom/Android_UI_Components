package kh.com.sela.android.topbartype.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.sela.android.topbartype.data.base.ComponentModel
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.data.base.BaseUiState.Success
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeVM(private val homeRipository: HomeRipository = HomeRipository()) : ViewModel() {
    private var _messageUiState: MutableStateFlow<BaseUiState<List<String>>> = MutableStateFlow(
        BaseUiState.None
    )
    var messageUiState = _messageUiState.asStateFlow()

    private var _componentList: MutableStateFlow<BaseUiState<List<ComponentModel>>> =
        MutableStateFlow(
            BaseUiState.None
        )

    var componentList = _componentList.asStateFlow()

    //    fun getMessage(){
//        viewModelScope.launch {
//            _messageUiState.emit(BaseUiState.Loading)
//            delay(timeMillis = 2000)
//            _messageUiState.emit(
//                BaseUiState.Success(data = homeRipository.getMessage()),
//
//            )
//        }
//    }
    fun getComponentList() {
        viewModelScope.launch {
            _componentList.emit(BaseUiState.Loading)
            delay(timeMillis = 500)
            homeRipository.getMessage().collect {result->
                _componentList.emit(Success(data = result))
            }

        }
    }


}

