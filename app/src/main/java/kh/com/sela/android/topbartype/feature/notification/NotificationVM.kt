package kh.com.sela.android.topbartype.feature.home.notification

import androidx.lifecycle.ViewModel
import kh.com.sela.android.topbartype.model.NotificationResponse
import kotlinx.coroutines.flow.MutableStateFlow

class NotificationVM(val notificaitonRepository: NotificaitonRepository= NotificaitonRepository()): ViewModel() {
private var _notificationList: MutableStateFlow<List<NotificationResponse>> = MutableStateFlow(value = emptyList())
    var notificationList =_notificationList
    fun getNitificationList(){
        _notificationList.value=notificaitonRepository.getNotification()
    }



}