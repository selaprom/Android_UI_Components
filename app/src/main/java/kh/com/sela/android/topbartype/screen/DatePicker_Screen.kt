package kh.com.sela.android.topbartype.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickers(){
    var datePickerState= rememberDatePickerState()

    fun convertMillisToDate(millis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date(millis))
    }
    val selectedDate= datePickerState.selectedDateMillis?.let{
        convertMillisToDate(it)

    }?:""


    Scaffold(
        floatingActionButton = {
            FloatingActionButton({}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 68.dp,
                title = {
                    Text("Date Picker", color = MaterialTheme.colorScheme.surface)
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
            Modifier.padding(it).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            //horizontalAlignment = Alignment.CenterHorizontally

        ) {
            DatePicker(
                modifier = Modifier.padding(horizontal = 16.dp).clip(shape = RoundedCornerShape(size = 15.dp)),
                title = {
                    Text("Select Your Date", fontSize = 20.sp, modifier = Modifier.fillMaxWidth().padding(16.dp), textAlign = TextAlign.Center)

                        },
                state =datePickerState
            )
            Spacer(Modifier.height(16.dp))
            Text("Your Select Time: $selectedDate", Modifier.fillMaxWidth(),textAlign = TextAlign.Center, fontSize = 20.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDatePicker(){
    DatePickers()
}