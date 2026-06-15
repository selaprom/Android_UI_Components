package kh.com.sela.android.topbartype.feature.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.domain.model.base.NotificationResponse
import kh.com.sela.android.topbartype.domain.model.base.NotificationType
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNotification(
    notificationVM: NotificationVM= NotificationVM(),onBackScreen:()-> Unit={},onClickItem:(id:String)-> Unit={}
){
     val notificationList by notificationVM.notificationList.collectAsStateWithLifecycle()
    LaunchedEffect( Unit) {
        notificationVM.getNitificationList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackScreen) {
                        Icon(painter = painterResource(R.drawable.ic_arrow_back),contentDescription = null)
                    }
                },
                title = {Text("Notification")},
                modifier = Modifier,

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),

            )
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(notificationList) { notification ->
                NotificationCard(notification){
                    onClickItem(notification.notificationId)
                }
            }
        }
    }
}
fun formatTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
@Composable
fun NotificationCard(notification: NotificationResponse,onClick:()-> Unit) {

    val backgroundColor = if (notification.isRead) Color.White else Color(0xFFE8F0FE)

    val icon = when (notification.type) {
        NotificationType.SYSTEM -> Icons.Default.Settings
        NotificationType.PROMOTION -> Icons.Default.LocalOffer
        NotificationType.REMINDER -> Icons.Default.Event
        NotificationType.MESSAGE -> Icons.Default.Mail
    }

    Card(
        modifier = Modifier
            .clickable{
                onClick()
            }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF6200EE),
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = notification.title,
                        fontWeight = if (notification.isRead) FontWeight.Normal else FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = formatTime(notification.timestamp),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            // 🔴 Unread dot
            if (!notification.isRead) {
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.Red, shape = CircleShape)
                )
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun  PreviewScreenNotificatiion(){
    TopBarTypeTheme{
        ScreenNotification()
    }
}