package kh.com.sela.android.topbartype.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen_BottomBarWithFloating(){
    var isSelectedIndex by remember { mutableStateOf(0) }

    Scaffold(


        topBar = {
            CenterAlignedTopAppBar(
                expandedHeight = 68.dp,
                title = {
                    Text("BottomBar", color = MaterialTheme.colorScheme.surface)
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
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(R.color.purple_200),
                floatingActionButton = {
                    FloatingActionButton(onClick = {}) {
                        Icon(painter = painterResource(R.drawable.ic_add,),contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isSelectedIndex=0
                    },
                      modifier = Modifier.background(
                          color = if (isSelectedIndex==0)Color.White else Color.Transparent,
                          shape = if (isSelectedIndex == 0) RoundedCornerShape(10.dp) else RectangleShape
                      )
                        ) {
                        Icon(painter = painterResource(R.drawable.ic_done),contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = {isSelectedIndex=1

                    },
                        modifier = Modifier.background(
                            color = if (isSelectedIndex==1)Color.White else Color.Transparent,
                            shape = if (isSelectedIndex == 1) RoundedCornerShape(10.dp) else RectangleShape
                        )
                        ) {
                        Icon(painter = painterResource(R.drawable.ic_edit),contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = {isSelectedIndex=2},
                        modifier = Modifier.background(
                            color = if (isSelectedIndex==2)Color.White else Color.Transparent,
                            shape = if (isSelectedIndex == 2) RoundedCornerShape(10.dp) else RectangleShape
                        )


                    ) {
                        Icon(painter = painterResource(R.drawable.ic_voice),contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = {isSelectedIndex=3},
                        modifier = Modifier.background(
                            color = if (isSelectedIndex==3)Color.White else Color.Transparent,
                            shape = if (isSelectedIndex == 3) RoundedCornerShape(10.dp) else RectangleShape
                        )
                        ) {
                        Icon(painter = painterResource(R.drawable.ic_image),contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }


                }
            )
        }
    ){padding->
        Column(
            Modifier
                .padding(padding)
                .padding(top = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


when(isSelectedIndex){
    0->{Text("Ticked Content")}
    1->{Text("Edit Content")}
    2->{Text("Microphone Content")}
    3->{Text("Image Content")}
}

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScreen_BottomBarWithFloating(){
    Screen_BottomBarWithFloating()
}