package kh.com.sela.android.topbartype.feature.home.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kh.com.sela.android.topbartype.model.NotificationResponse
import kh.com.sela.android.topbartype.model.base.BaseUiState
import kh.com.sela.android.topbartype.model.base.BaseUiState.Error
import kh.com.sela.android.topbartype.navigation.NotificationDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotificationVM(val notificaitonRepository: NotificaitonRepository= NotificaitonRepository()): ViewModel() {
private var _notificationList: MutableStateFlow<List<NotificationResponse>> = MutableStateFlow(value = emptyList())
    var notificationList =_notificationList.asStateFlow()

    var notificationDetailUiState : MutableStateFlow<BaseUiState<NotificationResponse>?> = MutableStateFlow(null)
    fun getNitificationList(){
        _notificationList.value=notificaitonRepository.getNotification()
    }


    fun getNotificationDetail(id: String){
        notificationDetailUiState.value= BaseUiState.Loading
        viewModelScope.launch {

            notificaitonRepository.getNotificationDetail(id).collect{response ->
                if (response!=null){
                    notificationDetailUiState.emit(BaseUiState.Success(response))
                }
                else{
                    notificationDetailUiState.emit(BaseUiState.Error(code = 1000,message= "Notification Not found"))
                }

            }
        }
    }


}