package kh.com.sela.android.topbartype.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class  HomeVM(private val  homeRipository: HomeRipository= HomeRipository()): ViewModel() {
    private  var _message : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    var message = _message.asStateFlow()
    fun getMessage(){
        viewModelScope.launch {
            _message.emit(homeRipository.getMessage())
        }
    }
}