package kh.com.sela.android.topbartype.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBar
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBarWithFloating
import kh.com.sela.android.topbartype.feature.appbar.Screen_TopAppBar
import kh.com.sela.android.topbartype.feature.bottomsheet.Screen_BottomSheet
import kh.com.sela.android.topbartype.feature.button.HomeButton
import kh.com.sela.android.topbartype.feature.card.ScreenCardView
import kh.com.sela.android.topbartype.feature.carousel.HorizontalMultiBrowseCarousels
import kh.com.sela.android.topbartype.feature.checkbox.PreViewCheckBox
import kh.com.sela.android.topbartype.feature.chip.ChipView
import kh.com.sela.android.topbartype.feature.datepicker.DatePickers
import kh.com.sela.android.topbartype.feature.dialog.ScreenDialog
import kh.com.sela.android.topbartype.feature.drawer.ScreenNavigationDrawer
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.menu.ScreenMenu
import kh.com.sela.android.topbartype.feature.navigationbar.ScreenBottomNavigationBar
import kh.com.sela.android.topbartype.feature.notification.ScreenNotification
import kh.com.sela.android.topbartype.feature.notification.ScreenNotificationDetail
import kh.com.sela.android.topbartype.feature.progressindicator.ScreenCicleProgressIndecator
import kh.com.sela.android.topbartype.feature.radiobutton.Screen_RadioButton
import kh.com.sela.android.topbartype.feature.slider.Screen_Slider
import kh.com.sela.android.topbartype.feature.snackbar.Screen_SnackBar
import kh.com.sela.android.topbartype.feature.switch.Screen_Switch
import kh.com.sela.android.topbartype.feature.tab.Screen_Tab
import kh.com.sela.android.topbartype.feature.textfield.Screen_TextField
import kh.com.sela.android.topbartype.feature.tooltip.Screen_ToolTip
import kh.com.sela.android.topbartype.feature.userapi.ScreenUserApi
import kotlinx.serialization.Serializable

//Define key that will identify content
data object Homes
data object Notification
data object Card
data object Tab
data object NavigationBar
data object BottomBarWithFloating
data object BottomSheet
data object ToolTip
data object NavigationDrawer
data object TextField
data object BottomBar
data object Dialog
data object SnackBar
data object ProgressIndicator
data object RadioButton
data object Slider
data object Switch
data object Menu
data object Chip
data object Button
data object DatePicker
data object Carousel
data object CheckBox
data object TopAppBar
data object UserApi

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
                    ScreenHomes(
                        onClickNotification = { backStack.add(Notification) },
                        onClickCard = { backStack.add(Card) },
                        onClickComponent = { componentKey -> backStack.add(componentKey) }
                    )
                }

                is Notification -> NavEntry(key) {
                    ScreenNotification(onBackScreen = { backStack.removeLastOrNull() }) { id ->
                        backStack.add(NotificationDetail(id = id))
                    }
                }

                is Card -> NavEntry(key) {
                    ScreenCardView { backStack.removeLastOrNull() }
                }

                is NotificationDetail -> NavEntry(key) {
                    ScreenNotificationDetail(
                        id = key.id,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }

                is Tab -> NavEntry(key) {
                    Screen_Tab (){}
                }

                is NavigationBar -> NavEntry(key) {
                    ScreenBottomNavigationBar (){}
                }

                is BottomBarWithFloating -> NavEntry(key) {
                    Screen_BottomBarWithFloating { backStack.removeLastOrNull() }
                }

                is BottomSheet -> NavEntry(key) {
                    Screen_BottomSheet (){}
                }

                is ToolTip -> NavEntry(key) {
                    Screen_ToolTip ()
                }

                is NavigationDrawer -> NavEntry(key) {
                    ScreenNavigationDrawer ()
                }

                is TextField -> NavEntry(key) {
                    Screen_TextField ()
                }

                is BottomBar -> NavEntry(key) {
                    Screen_BottomBar { backStack.removeLastOrNull() }
                }

                is Dialog -> NavEntry(key) {
                    ScreenDialog ()
                }

                is SnackBar -> NavEntry(key) {
                    Screen_SnackBar ()
                }

                is ProgressIndicator -> NavEntry(key) {
                    ScreenCicleProgressIndecator()
                }

                is RadioButton -> NavEntry(key) {
                    Screen_RadioButton ()
                }

                is Slider -> NavEntry(key) {
                    Screen_Slider ()
                }

                is Switch -> NavEntry(key) {
                    Screen_Switch ()
                }

                is Menu -> NavEntry(key) {
                    ScreenMenu ()
                }

                is Chip -> NavEntry(key) {
                    ChipView ()
                }

                is Button -> NavEntry(key) {
                    HomeButton ()
                }

                is DatePicker -> NavEntry(key) {
                    DatePickers ()
                }

                is Carousel -> NavEntry(key) {
                    HorizontalMultiBrowseCarousels ()
                }

                is CheckBox -> NavEntry(key) {
                    PreViewCheckBox ()
                }

                is TopAppBar -> NavEntry(key) {
                    Screen_TopAppBar { backStack.removeLastOrNull() }
                }
                is UserApi -> NavEntry(key) {
                    ScreenUserApi ()
                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}
