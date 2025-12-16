package kh.com.sela.android.topbartype.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBottomNavigationBar(){
var isChangState by remember { mutableIntStateOf(0) }
    data class NavigationBarItems(
        val  label: String,
        @DrawableRes val  icon:Int,


    )
    val lsItem= listOf<NavigationBarItems>(NavigationBarItems(label = "Home", icon = R.drawable.ic_home ),
        NavigationBarItems(label = "Video", icon = R.drawable.ic_video ,),
        NavigationBarItems(label = "Profile", icon = R.drawable.ic_profile ,)
        )


    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                windowInsets = BottomAppBarDefaults.windowInsets
            ) {

                lsItem.forEachIndexed { index, item ->
                    NavigationBarItem(

                        selected = isChangState==index,
                        onClick = {
                            isChangState = index

                                  },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.Green, selectedTextColor = Color.White, indicatorColor = Color.Blue, unselectedIconColor = Color.Black, unselectedTextColor = Color.Black),

                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "") },
                        label = {Text(item.label)}

                    )
                }

            }
        },

        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 68.dp,
                title = {
                    Text("Screen Menu", color = MaterialTheme.colorScheme.surface)
                },


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton({}){
                        Icon(Icons.Default.Menu, contentDescription = "", tint = MaterialTheme.colorScheme.surface)

                    }
                },
                actions = {
                    IconButton({

                    }){
                        Icon(Icons.Default.MoreVert, contentDescription = "", tint = MaterialTheme.colorScheme.surface)

                    }
                }

            )
        },
    ){it->
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(isChangState){
                0->Text("HomeView")
                1->Text("VideoView")
                2->Text("ProfileView")
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScreenBottomNavigationBar(){
    ScreenBottomNavigationBar()
}