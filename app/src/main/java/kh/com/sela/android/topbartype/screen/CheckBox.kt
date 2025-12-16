package kh.com.sela.android.topbartype.screen

import android.graphics.Paint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBoxs() {
    var isCheck =  remember { mutableStateListOf(false,false,false) }
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
                    Text("Horizontal_Carousel", color = MaterialTheme.colorScheme.surface)
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
    ){padding->
        Column(
            Modifier.padding(padding).padding(10.dp)
        ) {
            isCheck.forEachIndexed { index, isChecked ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Selec Option ${index+1}")
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = {isChecked->
                            isCheck[index]=isChecked

                        }
                    )
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreViewCheckBox(){
    CheckBoxs ()


}