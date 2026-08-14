package kh.com.sela.android.topbartype

//import android.R

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.app.ActivityCompat
import androidx.media3.common.util.Log
import androidx.media3.common.util.NotificationUtil.createNotificationChannel
import androidx.media3.common.util.UnstableApi
import dagger.hilt.android.AndroidEntryPoint
import kh.com.sela.android.topbartype.service.firebase.NotificationUtil
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.LoadingContent
import kh.com.sela.android.topbartype.Util.LoadingUtil
import kh.com.sela.android.topbartype.feature.home.ScreenHomes
import kh.com.sela.android.topbartype.navigation.AppNavigation
import kh.com.sela.android.topbartype.feature.dialog.AlertDialog
import kh.com.sela.android.topbartype.feature.chip.ChipView
import kh.com.sela.android.topbartype.feature.datepicker.DatePickers
import kh.com.sela.android.topbartype.feature.card.ElevetionCard
import kh.com.sela.android.topbartype.feature.button.HomeButton
import kh.com.sela.android.topbartype.feature.carousel.HorizontalMultiBrowseCarousels
import kh.com.sela.android.topbartype.feature.appbar.MediumTopAppBar
import kh.com.sela.android.topbartype.feature.appbar.NormalTopBar
import kh.com.sela.android.topbartype.feature.checkbox.PreViewCheckBox
import kh.com.sela.android.topbartype.feature.chip.RowAssistChip
import kh.com.sela.android.topbartype.feature.navigationbar.ScreenBottomNavigationBar
import kh.com.sela.android.topbartype.feature.progressindicator.ScreenCicleProgressIndecator
import kh.com.sela.android.topbartype.feature.dialog.ScreenDialog
import kh.com.sela.android.topbartype.feature.menu.ScreenMenu
import kh.com.sela.android.topbartype.feature.drawer.ScreenNavigationDrawer
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBar
import kh.com.sela.android.topbartype.feature.appbar.Screen_BottomBarWithFloating
import kh.com.sela.android.topbartype.feature.bottomsheet.Screen_BottomSheet
import kh.com.sela.android.topbartype.feature.radiobutton.Screen_RadioButton
import kh.com.sela.android.topbartype.feature.slider.Screen_Slider
import kh.com.sela.android.topbartype.feature.snackbar.Screen_SnackBar
import kh.com.sela.android.topbartype.feature.switch.Screen_Switch
import kh.com.sela.android.topbartype.feature.tab.Screen_Tab
import kh.com.sela.android.topbartype.feature.textfield.Screen_TextField
import kh.com.sela.android.topbartype.feature.tooltip.Screen_ToolTip

/**
 * setup DI for android project
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var route by mutableStateOf<String?>(null)
    private var userId by mutableStateOf<String?>(null)




    @RequiresApi(Build.VERSION_CODES.TIRAMISU)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleNotificationIntent(intent)
        enableEdgeToEdge()
        setContent {

            val notificationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (!isGranted) {
                    Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
                } else {
                   NotificationUtil.createNotificationChannel(this@MainActivity)
                }
            }
            LaunchedEffect(Unit) {
                // Check grant notification
                if (
                    ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            TopBarTypeTheme {

                if (LoadingUtil.isLoading.value){
                    LoadingContent()
                }
                AppNavigation(route)
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        setIntent(intent)

        handleNotificationIntent(intent)
    }

    @OptIn(UnstableApi::class)
    private fun handleNotificationIntent(intent: Intent?) {

        route = intent?.getStringExtra("route")
        userId = intent?.getStringExtra("userId")

        Log.d("NOTIFICATION_CLICK", "route = $route")
        Log.d("NOTIFICATION_CLICK", "userId = $userId")
    }

}

/**
 * Room Database Requirement
 * 1.Create task management using
 * -room database
 * -dependency injection
 * -viewmodel
 * -compose
 * -navigation
 * -follow MVVM architecture
 * 2.Get Task List
 * 3.Create Task
 * 4.Update Task
 * 5.Delete Task
 * 6.Get Task By Id
 * 7.Get Task By Title
 * 8.Filter Task By Status
 * 9.Define Model
 * -taskModel
 * -taskName
 * taskDescription
 * taskCompleteYN
 *
 */

