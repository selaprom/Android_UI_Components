package kh.com.sela.android.topbartype.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kh.com.sela.android.topbartype.feature.androidXCamera.ScreenAndroidXCamera
import kh.com.sela.android.topbartype.feature.androidXCamera.ScreenCameraView
import kh.com.sela.android.topbartype.feature.androidXCamera.ScreenPreviewImage
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBar
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBarWithFloating
import kh.com.sela.android.topbartype.feature.appbar.Screen_TopAppBar
import kh.com.sela.android.topbartype.feature.bottomsheet.Screen_BottomSheet
import kh.com.sela.android.topbartype.feature.button.HomeButton
import kh.com.sela.android.topbartype.feature.camera.ScreenCameraLauncher
import kh.com.sela.android.topbartype.feature.card.ScreenCardView
import kh.com.sela.android.topbartype.feature.carousel.HorizontalMultiBrowseCarousels
import kh.com.sela.android.topbartype.feature.checkbox.PreViewCheckBox
import kh.com.sela.android.topbartype.feature.chip.ChipView
import kh.com.sela.android.topbartype.feature.datepicker.DatePickers
import kh.com.sela.android.topbartype.feature.dialog.ScreenDialog
import kh.com.sela.android.topbartype.feature.drawer.ScreenNavigationDrawer
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.feature.internetstatechange.ScreenInternetStateChange
import kh.com.sela.android.topbartype.feature.location.ScreenLocation
import kh.com.sela.android.topbartype.feature.mediapicker.ScreenSelectedMultiplePhotos
import kh.com.sela.android.topbartype.feature.mediapicker.ScreenSelectedMultipleVideos
import kh.com.sela.android.topbartype.feature.mediapicker.ScreenSelectedSinglePhoto
import kh.com.sela.android.topbartype.feature.mediapicker.ScreenSelectedSingleVideo
import kh.com.sela.android.topbartype.feature.mediapicker.ScreenSelectedVideosAndPhotos
import kh.com.sela.android.topbartype.feature.menu.ScreenMenu
import kh.com.sela.android.topbartype.feature.navigationbar.ScreenBottomNavigationBar
import kh.com.sela.android.topbartype.feature.notification.ScreenNotification
import kh.com.sela.android.topbartype.feature.notification.ScreenNotificationDetail
import kh.com.sela.android.topbartype.feature.postnotification.ScreenPostNotification
import kh.com.sela.android.topbartype.feature.progressindicator.ScreenCicleProgressIndecator
import kh.com.sela.android.topbartype.feature.radiobutton.Screen_RadioButton
import kh.com.sela.android.topbartype.feature.roomdatabase.ScreenCreateTask
import kh.com.sela.android.topbartype.feature.roomdatabase.ScreenRoomDatabase
import kh.com.sela.android.topbartype.feature.screeninformatiion.ScreenDeviceScreenInformation
import kh.com.sela.android.topbartype.feature.slider.Screen_Slider
import kh.com.sela.android.topbartype.feature.snackbar.Screen_SnackBar
import kh.com.sela.android.topbartype.feature.switch.Screen_Switch
import kh.com.sela.android.topbartype.feature.tab.Screen_Tab
import kh.com.sela.android.topbartype.feature.textfield.Screen_TextField
import kh.com.sela.android.topbartype.feature.tooltip.Screen_ToolTip
import kh.com.sela.android.topbartype.feature.userapi.ScreenUserApi
import kotlinx.serialization.Serializable

private const val ANIMATION_DURATION = 300

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(route: String? = null) {
    val backStack = remember { mutableStateListOf<Any>(Homes) }
    LaunchedEffect(route) {
        when (route) {
            "room-db" -> {
                backStack.add(RoomDatabase)
            }

            "notification-detail" -> {
                backStack.add(NotificationDetail)
            }

            "user-api" -> {
                backStack.add(UserApi)
            }
        }

    }



    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth / 3 },
                animationSpec = tween(ANIMATION_DURATION)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(ANIMATION_DURATION)
            )
        },
        entryProvider = entryProvider {
            entry<Homes> { key ->
                ScreenHomes(
                    onClickNotification = { backStack.add(Notification) },
                    onClickCard = { backStack.add(Card) },
                    onClickComponent = { componentKey -> backStack.add(componentKey) })
            }
            entry<Notification> { key ->
                ScreenNotification(onBackScreen = { backStack.removeLastOrNull() }) { id ->
                    backStack.add(
                        NotificationDetail(id = id)
                    )
                }
            }
            entry<Card> { ScreenCardView { backStack.removeLastOrNull() } }
            entry<NotificationDetail> { key ->
                ScreenNotificationDetail(
                    id = key.id,
                    onBack = { backStack.removeLastOrNull() })
            }
            entry<Tab> { Screen_Tab {} }
            entry<NavigationBar> { ScreenBottomNavigationBar {} }
            entry<BottomBarWithFloating> { Screen_BottomBarWithFloating { backStack.removeLastOrNull() } }
            entry<BottomSheet> { Screen_BottomSheet() {} }
            entry<ToolTip> { Screen_ToolTip() }
            entry<NavigationDrawer> { ScreenNavigationDrawer() }
            entry<TextField> { Screen_TextField() }
            entry<BottomBar> { Screen_BottomBar { backStack.removeLastOrNull() } }
            entry<Dialog> { ScreenDialog() }
            entry<SnackBar> { Screen_SnackBar() }
            entry<ProgressIndicator> { ScreenCicleProgressIndecator() }
            entry<RadioButton> { Screen_RadioButton() }
            entry<Slider> { Screen_Slider() }
            entry<Switch> { Screen_Switch() }
            entry<Menu> { ScreenMenu() }
            entry<Chip> { ChipView() }
            entry<Button> { HomeButton() }
            entry<DatePicker> { DatePickers() }
            entry<Carousel> { HorizontalMultiBrowseCarousels() }
            entry<CheckBox> { PreViewCheckBox() }
            entry<TopAppBar> { Screen_TopAppBar { backStack.removeLastOrNull() } }
            entry<UserApi> { ScreenUserApi() }
            entry<PostNotification> { ScreenPostNotification { backStack.removeLastOrNull() } }
            entry<SelectedSinglePhoto> { ScreenSelectedSinglePhoto { backStack.removeLastOrNull() } }
            entry<SelectedMultiplePhotos> { ScreenSelectedMultiplePhotos { backStack.removeLastOrNull() } }
            entry<SelectedSingleVideo> { ScreenSelectedSingleVideo { backStack.removeLastOrNull() } }
            entry<SelectedMultipleVideos> { ScreenSelectedMultipleVideos { backStack.removeLastOrNull() } }
            entry<SelectedVideosAndPhotos> { ScreenSelectedVideosAndPhotos { backStack.removeLastOrNull() } }
            entry<CameraLauncher> { ScreenCameraLauncher { backStack.removeLastOrNull() } }
            entry<RoomDatabase> {
                ScreenRoomDatabase(
                    onBackClick = { backStack.removeLastOrNull() },
                    onCreateTask = { backStack.add(CreateTask()) },
                    onEditTask = { task -> backStack.add(CreateTask(task)) })
            }
            entry<CreateTask> { key -> ScreenCreateTask(key.task) { backStack.removeLastOrNull() } }
            entry<GetLocation> { ScreenLocation { backStack.removeLastOrNull() } }
            entry<AndroidXCamera> {
                ScreenAndroidXCamera(
                    onBackClick = { backStack.removeLastOrNull() },
                    onOpenCamera = { backStack.add(CameraAndroidPreview) })
            }
            entry<CameraAndroidPreview> {
                ScreenCameraView(
                    onClose = { backStack.removeLastOrNull() },
                    onImageCaptured = { backStack.add(PreviewImage(it.toString())) })
            }
            entry<PreviewImage> { key ->
                ScreenPreviewImage(
                    onBack = { backStack.removeLastOrNull() },
                    imageUri = key.imageUri.toUri()
                )
            }
            entry<DeviceScreenInformation> {
                ScreenDeviceScreenInformation (){
                    backStack.removeLastOrNull()
                }
            }
            entry<InternetStateChange> { ScreenInternetStateChange { backStack.removeLastOrNull() } }
        })
}
