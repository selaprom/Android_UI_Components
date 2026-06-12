package kh.com.sela.android.topbartype.navigation

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

data object PostNotification
data object SelectedSinglePhoto
data object SelectedMultiplePhotos

data object SelectedSingleVideo
data object SelectedMultipleVideos
data object SelectedVideosAndPhotos

data object CameraLauncher

@Serializable
data class NotificationDetail(val id: String)
