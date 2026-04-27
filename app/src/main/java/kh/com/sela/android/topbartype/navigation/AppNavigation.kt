package kh.com.sela.android.topbartype.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.home.notification.ScreenNotification

//Define key that will identify content
data object Homes
data object Notification

@Composable
fun AppNavigation() {

    val backStack = remember { mutableStateListOf<Any>(Homes) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Homes -> NavEntry(key) {
                    ScreenHomes(){
                        backStack.add(Notification)
                    }
                }
                is Notification -> NavEntry(key){
                    ScreenNotification(){
                        backStack.removeLastOrNull()
                    }
                }



                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}