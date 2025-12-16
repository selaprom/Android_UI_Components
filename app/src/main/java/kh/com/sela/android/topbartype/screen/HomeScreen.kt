package kh.com.sela.android.topbartype.screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.R

/**
 * Normal AppTopBar
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NormalTopBar(){
    val labels=listOf("Computer","Phone","Accessory")
    var selectIndex by remember { mutableIntStateOf(0) }


    return Scaffold(
        topBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 8.dp, shape = RectangleShape)
            ){
                TopAppBar(


                    modifier = Modifier.fillMaxWidth(),

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Cyan,
                        titleContentColor = Color.Red
                    ),

                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Share, contentDescription = "Button Search")
                        }
                        BadgedBox(
                            badge = { Badge(
                                containerColor = Color.Red, modifier = Modifier.size(20.dp ),
                            ) { Text("12", color = Color.White, maxLines = 1, overflow = TextOverflow.Visible, fontSize = 10.sp) } }
                        ) {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Notifications, contentDescription = "Button Notification")
                            }
                        }
                        IconButton(onClick = {
                            println("You Click Setting button")
                        }) {
                            Icon(Icons.Default.Settings, contentDescription = "Button Setting", tint = Color.Blue)
                        }

                    },


                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Menu Button")
                        }
                    },

                    title = {Text("Normal TopBar", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,color = Color.Black, modifier = Modifier.fillMaxWidth())},)
            }
        }
    ) { paddings-> Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(paddings)
            .fillMaxSize()
            .padding(12.dp)
    ){
        IconButton(
            modifier = Modifier.background(color = Color.Blue, shape = RoundedCornerShape(size = 10.dp)),
            // colors = IconButtonDefaults.iconButtonColors(containerColor = colorResource(R.color.holo_purple)),
            onClick = {
                println("you click Save Button")
            }) {
            // Icon(Icons.Default.CheckCircle, contentDescription = "Icon Save")
            Icon(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = "Icon Save", Modifier.size(25.dp), tint = Color.Yellow)
        }
        Spacer(Modifier
            .height(12.dp)
            .fillMaxWidth())
        IconButton(onClick = {}, modifier = Modifier.background(color = Color.Green, shape = RectangleShape)) {
            Icon(contentDescription = "Icon Add",painter = painterResource(id = R.drawable.ic_add), modifier = Modifier.size(25.dp), tint = Color.White)
        }
        Spacer(Modifier
            .height(12.dp)
            .fillMaxWidth())
        IconButton(onClick = {}, modifier = Modifier.background(color = Color.Magenta, shape = CircleShape)) {
            Icon(painter = painterResource(R.drawable.ic_favorit), contentDescription = "Icon Favorit", tint = Color.White, modifier = Modifier.size(25.dp))
        }
        Spacer(Modifier.height(12.dp))
        Button(

            onClick = {
                println("You Click Continue")
            }, colors =   ButtonDefaults.buttonColors(containerColor = Color.Transparent), modifier = Modifier
                .background(color = Color.Green, shape = CircleShape)
                .fillMaxWidth() ) {
            Text("Continue", fontFamily = FontFamily(Font(R.font.ourfit_varible)),color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text("Welcome to Android Developer", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        SingleChoiceSegmentedButtonRow() {
            labels.forEachIndexed { index, label ->
                val isselected=selectIndex==index
                SegmentedButton(
                    shape = RoundedCornerShape(12.dp),
                    selected = isselected,
                    onClick = {
                        selectIndex=index
                    },

                    enabled = true,
                    label = {
                        Text(label)
                    },
                    icon = {
                        SegmentedButtonDefaults.Icon(active = isselected)
                    }




                )
            }
        }


    } }



}

/**
 * Center AppTopBar
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(function: () -> Unit) {
    val  scrollBehavior= TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary, titleContentColor = MaterialTheme.colorScheme.onPrimary),
                title = {Text("Center TopAppBar")},
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Icon back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "Icon Setting")
                    }

                }
            )
        }
    ) {it->
        Row(
            Modifier.padding(it)
        ) {
            Button(onClick = {}) {
                Text("Hello World")

            }
            Button(onClick = {}) {
                Text("Hello Android")


            }
            Button(onClick = {}) {
                Text("Hello Flutter")


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MediumTopAppBar(){
    val scrollBehavior= TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    return Scaffold(
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                title = {
                    Text("Medium TopAppBar",)
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Icon Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Icon Setting",
                            tint = Color.White
                        )
                    }
                },
                scrollBehavior = scrollBehavior


            )
        },
        bottomBar = {BottomAppBar()}
    ) {innerpadding->
        Column(Modifier.padding(innerpadding)) { }
    }
}
@Composable
fun BottomAppBar(){
    return BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,


        actions = {
            IconButton(onClick = {}) { Icon(Icons.Filled.Check, contentDescription = "Icon Check") }
            IconButton(onClick = {}) { Icon(Icons.Filled.Edit, contentDescription = "Icon Edit") }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.LocationOn,
                    contentDescription = "Icon Check"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Icon Check"
                )
            }
        },
        floatingActionButton = {

            FloatingActionButton(
                onClick = {},
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Add, contentDescription = "Icon Add")
                }
            }
        },

        )
}

@Preview(showBackground = true)
@Composable
fun ShowPreView(){
     // NormalTopBar()
   //  CenterTopAppBar()
   // MediumTopAppBar()
}