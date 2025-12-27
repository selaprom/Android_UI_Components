package kh.com.sela.android.topbartype

//import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.screen.AlertDialog
import kh.com.sela.android.topbartype.screen.ChipView
import kh.com.sela.android.topbartype.screen.DatePickers
import kh.com.sela.android.topbartype.screen.ElevetionCard
import kh.com.sela.android.topbartype.screen.HorizontalMultiBrowseCarousels
import kh.com.sela.android.topbartype.screen.MediumTopAppBar
import kh.com.sela.android.topbartype.screen.NormalTopBar
import kh.com.sela.android.topbartype.screen.PreViewCheckBox
import kh.com.sela.android.topbartype.screen.RowAssistChip
import kh.com.sela.android.topbartype.screen.ScreenBottomNavigationBar
import kh.com.sela.android.topbartype.screen.ScreenCicleProgressIndecator
import kh.com.sela.android.topbartype.screen.ScreenDialog
import kh.com.sela.android.topbartype.screen.ScreenMenu
import kh.com.sela.android.topbartype.screen.ScreenNavigationDrawer
import kh.com.sela.android.topbartype.screen.Screen_BottomSheet
import kh.com.sela.android.topbartype.screen.Screen_RadioButton
import kh.com.sela.android.topbartype.screen.Screen_Slider
import kh.com.sela.android.topbartype.screen.Screen_SnackBar
import kh.com.sela.android.topbartype.screen.Screen_Switch
import kh.com.sela.android.topbartype.screen.Screen_Tab


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopBarTypeTheme {
              //  NormalTopBar()
              //  CenterTopAppBar()
               // MediumTopAppBar()
               // ElevetionCard()
              //  HorizontalMultiBrowseCarousels()
               // PreViewCheckBox()
               // ChipView()
              //  RowAssistChip()
               // DatePickers()
               // AlertDialog()
               // ScreenDialog()
              // ScreenCicleProgressIndecator()
               // ScreenMenu()
               // ScreenBottomNavigationBar()
               // ScreenNavigationDrawer()
             //   Screen_RadioButton()
               // Screen_BottomSheet()
               // Screen_Slider()
              //  Screen_SnackBar()
               // Screen_Switch()
                Screen_Tab()
            }
        }
    }
}


