package kh.com.sela.android.topbartype.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.sela.android.topbartype.model.base.BaseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class  HomeVM(private val  homeRipository: HomeRipository= HomeRipository()): ViewModel() {
    private  var _messageUiState : MutableStateFlow<BaseUiState<List<String>>> = MutableStateFlow(
        BaseUiState.None)
    var messageUiState = _messageUiState.asStateFlow()
    fun getMessage(){
        viewModelScope.launch {
            _messageUiState.emit(BaseUiState.Loading)
            delay(timeMillis = 2000)
            _messageUiState.emit(
                BaseUiState.Success(data = homeRipository.getMessage()),

            )
        }
    }
    init {
        getMessage()
    }
}

