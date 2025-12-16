package kh.com.sela.android.topbartype.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDialog(){
var isShow by remember { mutableStateOf(false) }
        val context= LocalContext.current
    Scaffold(
        bottomBar = {
            FilledTonalButton(onClick = {
                isShow=true
            },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth().wrapContentHeight().navigationBarsPadding()
                ) {
                Text("Show Dailog")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 68.dp,
                title = {
                    Text("Show Dailog", color = MaterialTheme.colorScheme.surface)
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
                    IconButton({}){
                        Icon(Icons.Default.Notifications, contentDescription = "", tint = MaterialTheme.colorScheme.surface)
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
            if (isShow) {
                Dialog(

                    onDismissRequest = {
                        isShow=false
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().height(500.dp).background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))

                    ) {
                        Box(
                            Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Are You Want To Delete!!!", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        }
                        Row() {
                            FilledTonalButton(onClick = {
                                isShow=false
                            },
                                modifier = Modifier.height(35.dp)
                                    .weight(1f)) {

                                Text("Cancel")
                            }
                            FilledTonalButton(onClick = {
                                isShow=false
                                val toast= Toast.makeText(context,"deleted", Toast.LENGTH_SHORT)
                                toast.show()
                            },
                                modifier = Modifier.height(35.dp)
                                    .weight(1f)) {

                                Text("Confirm")
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScreenDialog(){
    ScreenDialog()
}