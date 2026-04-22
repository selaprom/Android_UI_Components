package kh.com.sela.android.topbartype.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class  HomeVM: ViewModel() {
    private  var _message : MutableStateFlow<String> = MutableStateFlow("")
    var message = _message.asStateFlow()
    fun getMessage(){
        viewModelScope.launch {
            _message.emit("this is my message.")
        }
    }
}