package kh.com.sela.android.topbartype.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.android.topbartype.feature.card.ScreenCardView
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.home.notification.ScreenNotification
import kh.com.sela.android.topbartype.feature.notification.ScreenNotificationDetail
import kotlinx.serialization.Serializable

//Define key that will identify content
data object Homes
data object Notification
data object Card

@Serializable
data class NotificationDetail(val id: String)

@Composable
fun AppNavigation() {

    val backStack = remember { mutableStateListOf<Any>(Homes) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Homes -> NavEntry(key) {
                    ScreenHomes(onClickNotification = {

                            backStack.add(Notification)

                    }) {
                        backStack.add(Card)
                    }
                }

                is Notification -> NavEntry(key) {
                    ScreenNotification(onBackScreen = {backStack.removeLastOrNull()}) {id ->
                        backStack.add(NotificationDetail(id = id,))
                    }
                }

                is Card -> NavEntry(key){
                    ScreenCardView(){
                        backStack.removeLastOrNull()
                    }
                }
                is NotificationDetail -> NavEntry(key){
                    ScreenNotificationDetail(
                        id = key.id,

                        onBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}