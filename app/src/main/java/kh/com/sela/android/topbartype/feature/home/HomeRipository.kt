package kh.com.sela.android.topbartype.feature.home

import kh.com.sela.android.topbartype.data.base.ComponentModel
import kh.com.sela.android.topbartype.navigation.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRipository {
    private val componentList = listOf(
        ComponentModel(1, "Top App Bar", "Different types of Top App Bars", "ic_appbar", TopAppBar),
        ComponentModel(2, "Bottom App Bar", "Bottom App Bar examples", "ic_bottombar", BottomBar),
        ComponentModel(3, "Navigation Bar", "Standard Bottom Navigation", "ic_nav", NavigationBar),
        ComponentModel(4, "Navigation Drawer", "Side navigation drawer", "ic_drawer", NavigationDrawer),
        ComponentModel(5, "Tabs", "Scrollable and Fixed tabs", "ic_tab", Tab),
        ComponentModel(6, "Buttons", "Various button styles", "ic_button", Button),
        ComponentModel(7, "Chips", "Assist, Filter, Input, Suggestion chips", "ic_chip", Chip),
        ComponentModel(8, "Cards", "Elevated, Filled, Outlined cards", "ic_card", Card),
        ComponentModel(9, "Checkboxes", "Selection controls", "ic_checkbox", CheckBox),
        ComponentModel(10, "Radio Buttons", "Single selection controls", "ic_radio", RadioButton),
        ComponentModel(11, "Switches", "On/Off controls", "ic_switch", Switch),
        ComponentModel(12, "Sliders", "Range selection", "ic_slider", Slider),
        ComponentModel(13, "Progress Indicators", "Circular and Linear progress", "ic_progress", ProgressIndicator),
        ComponentModel(14, "Dialogs", "Alert and Custom dialogs", "ic_dialog", Dialog),
        ComponentModel(15, "Bottom Sheets", "Modal and Standard bottom sheets", "ic_bottomsheet", BottomSheet),
        ComponentModel(16, "Snackbars", "Brief messages", "ic_snackbar", SnackBar),
        ComponentModel(17, "Tooltips", "Descriptive text on long press", "ic_tooltip", ToolTip),
        ComponentModel(18, "Date Pickers", "Date selection dialogs", "ic_datepicker", DatePicker),
        ComponentModel(19, "Menus", "Dropdown menus", "ic_menu", Menu),
        ComponentModel(20, "TextFields", "Input fields", "ic_textfield", TextField),
        ComponentModel(21, "Carousel", "Horizontal image carousel", "ic_carousel", Carousel),
        ComponentModel(22, "UsersApi", "Horizontal image carousel", "ic_carousel", UserApi),
        ComponentModel(23, "PostNotification", "Post Notification", "ic_carousel", PostNotification),
        ComponentModel(24, "SelectedSinglePhoto", "Selected Single Photo", "ic_carousel", SelectedSinglePhoto),
        ComponentModel(25, "SelectedMultiplePhotos", "Selected Multiple Photos", "ic_carousel", SelectedMultiplePhotos),
        ComponentModel(26, "SelectedSingleVideo", "Selected Single Video", "ic_carousel", SelectedSingleVideo),
        ComponentModel(27, "SelectedMultipleVideos", "Selected Multiple Videos", "ic_carousel", SelectedMultipleVideos),
        ComponentModel(28, "SelectedVideosAndPhotos", "Selected Videos And Photos", "ic_carousel", SelectedVideosAndPhotos),
        ComponentModel(29, "CameraLauncher", "Camera Launcher", "ic_carousel", CameraLauncher),
        ComponentModel(30, "RoomDatabase", "Room Database", "ic_carousel", RoomDatabase),
        ComponentModel(31, "CreateTask", "Create Task", "ic_carousel", CreateTask),
        )


    fun getComponentList(): List<ComponentModel> {
        return componentList
    }

    fun getMessage(): Flow<List<ComponentModel>> {
        return flow {
            emit(componentList)
        }
    }
}
