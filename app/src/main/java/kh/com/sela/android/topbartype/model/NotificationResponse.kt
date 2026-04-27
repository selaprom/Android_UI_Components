package kh.com.sela.android.topbartype.model

data class NotificationResponse(
    val notificationId: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val isRead: Boolean,
    val type: NotificationType
)

enum class NotificationType {
    MESSAGE,
    SYSTEM,
    PROMOTION,
    REMINDER
}