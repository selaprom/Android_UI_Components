package kh.com.sela.android.topbartype.feature.notification

import kh.com.sela.android.topbartype.domain.model.base.NotificationResponse
import kh.com.sela.android.topbartype.domain.model.base.NotificationType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificaitonRepository {
    val notificationList = listOf(
        NotificationResponse(
            "1",
            "New Message",
            "You received a new message",
            System.currentTimeMillis(),
            false,
            NotificationType.MESSAGE
        ),
        NotificationResponse("2", "System Update", "App updated successfully", System.currentTimeMillis(), true, NotificationType.SYSTEM),
        NotificationResponse("3", "Promo", "Get 50% discount today!", System.currentTimeMillis(), false, NotificationType.PROMOTION),
        NotificationResponse("4", "Reminder", "Meeting at 3 PM", System.currentTimeMillis(), false, NotificationType.REMINDER),
        NotificationResponse("5", "New Message", "John sent you a message", System.currentTimeMillis(), true, NotificationType.MESSAGE),
        NotificationResponse("6", "System Alert", "Password changed", System.currentTimeMillis(), true, NotificationType.SYSTEM),
        NotificationResponse("7", "Promo", "Buy 1 Get 1 Free!", System.currentTimeMillis(), false, NotificationType.PROMOTION),
        NotificationResponse("8", "Reminder", "Doctor appointment tomorrow", System.currentTimeMillis(), false, NotificationType.REMINDER),
        NotificationResponse("9", "New Message", "Anna: Hi there!", System.currentTimeMillis(), false, NotificationType.MESSAGE),
        NotificationResponse("10", "System", "Backup completed", System.currentTimeMillis(), true, NotificationType.SYSTEM),
        NotificationResponse("11", "Promo", "Flash sale starts now!", System.currentTimeMillis(), false, NotificationType.PROMOTION),
        NotificationResponse("12", "Reminder", "Submit report", System.currentTimeMillis(), true, NotificationType.REMINDER),
        NotificationResponse("13", "New Message", "Team chat updated", System.currentTimeMillis(), false, NotificationType.MESSAGE),
        NotificationResponse("14", "System", "New login detected", System.currentTimeMillis(), false, NotificationType.SYSTEM),
        NotificationResponse("15", "Promo", "Special offer just for you", System.currentTimeMillis(), false, NotificationType.PROMOTION),
        NotificationResponse("16", "Reminder", "Workout time!", System.currentTimeMillis(), true, NotificationType.REMINDER),
        NotificationResponse("17", "New Message", "Client replied", System.currentTimeMillis(), false, NotificationType.MESSAGE),
        NotificationResponse("18", "System", "Storage almost full", System.currentTimeMillis(), false, NotificationType.SYSTEM),
        NotificationResponse("19", "Promo", "Free shipping available", System.currentTimeMillis(), true, NotificationType.PROMOTION),
        NotificationResponse("20", "Reminder", "Call your friend", System.currentTimeMillis(), false, NotificationType.REMINDER)
    )
    fun getNotification(): List<NotificationResponse>{
        return notificationList
    }

    suspend fun getNotificationDetail(id: String): Flow<NotificationResponse?>{
        delay(1000)
        return flow {

            val notification = notificationList.filter { it.notificationId==id }
            if (notification.isNotEmpty()){
                emit(notification[0])
            }
            else{
                emit(null)
            }
        }
    }
}