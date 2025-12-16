package kh.com.sela.android.topbartype.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AssistChip
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipView(){
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
                    Text("AssistChip", color = MaterialTheme.colorScheme.surface)
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
            Modifier.padding(padding).padding(10.dp).verticalScroll(state = rememberScrollState())
        ) {

            for(i in 1..100){
                AssistChip(
                    onClick = {},
                    leadingIcon = {
                        Icon(Icons.Default.AddCircle, contentDescription = "")
                    },
                    trailingIcon = {
                        Icon(Icons.Default.CheckCircle, contentDescription = "")
                    },
                    label = {
                        Text("Chip $i")
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview(){
    ChipView()
}