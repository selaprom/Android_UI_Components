package kh.com.sela.android.topbartype.screen

import TopAppBar
import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import kh.com.sela.android.topbartype.R.*
import kh.com.sela.android.topbartype.model.DashboardModel
import kh.com.sela.android.topbartype.model.OtherServiceItemModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeButton(){


    val items = arrayListOf<OtherServiceItemModel>(
        OtherServiceItemModel(
            id = "001",
            label = "Balance",
            iconRes = drawable.ic_dollar,
            iconColorRes = color.purple_200,
            backgroundColorRes = color.purple_500,

        ),
        OtherServiceItemModel(
            id = "002",
            label = "Add Money",
            iconRes = drawable.ic_wallet,
            iconColorRes = color.purple_500,
            backgroundColorRes = color.purple_200,

            ),
        OtherServiceItemModel(
            id = "003",
            label = "Send",
            iconRes = drawable.ic_send,
            iconColorRes = color.teal_200,
            backgroundColorRes = color.purple_500,

            ),
        OtherServiceItemModel(
            id = "004",
            label = "Recieved",
            iconRes = drawable.ic_recieve,
            iconColorRes = color.teal_700,
            backgroundColorRes = color.white,

            ),
        OtherServiceItemModel(
            id = "001",
            label = "Balance",
            iconRes = drawable.ic_dollar,
            iconColorRes = color.purple_200,
            backgroundColorRes = color.purple_500,

            ),
        OtherServiceItemModel(
            id = "002",
            label = "Add Money",
            iconRes = drawable.ic_wallet,
            iconColorRes = color.purple_500,
            backgroundColorRes = color.purple_200,

            ),
        OtherServiceItemModel(
            id = "003",
            label = "Send",
            iconRes = drawable.ic_send,
            iconColorRes = color.teal_200,
            backgroundColorRes = color.purple_500,

            ),
        OtherServiceItemModel(
            id = "004",
            label = "Recieved",
            iconRes = drawable.ic_recieve,
            iconColorRes = color.teal_700,
            backgroundColorRes = color.white,

            )
    )


    Scaffold(

        topBar = {
            TopAppBar()
        },
    ){it->
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Top,
            //horizontalAlignment = Alignment.CenterHorizontally

        ) {
            MainCardDashBoard()
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(260.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                    ) {
                    Text("Other Service", modifier = Modifier
                        .fillMaxWidth(),
                        fontSize = 20.sp


                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        contentPadding = PaddingValues(16.dp),
                        userScrollEnabled = false,
                        verticalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                        items(items){i->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(shape = RoundedCornerShape(size = 16.dp), color = colorResource(i.backgroundColorRes))
                                        .size(56.dp)

                                    ,
                                    contentAlignment = Alignment.Center
                                ){
                                    IconButton(onClick = {
                                        println("======>this is $i")
                                    }) {
                                        Icon(painter = painterResource(i.iconRes),contentDescription = null, modifier = Modifier.size(30.dp), tint = colorResource(i.iconColorRes))
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(i.label, color = MaterialTheme.colorScheme.primary, fontSize = 15.sp)


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
fun PreviewHomeButton(){
    HomeButton()
}

@Composable
fun MainCardDashBoard(){
    val items = arrayListOf<DashboardModel>(
        DashboardModel(
            id = "001",
            label = "Balance",
            iconRes = drawable.ic_dollar,
        ),
        DashboardModel(
            id = "002",
            label = "Add Money",
            iconRes = drawable.ic_wallet,
        ),
        DashboardModel(
            id = "003",
            label = "Send",
            iconRes = drawable.ic_send,
        ),
        DashboardModel(
            id = "004",
            label = "Recieved",
            iconRes = drawable.ic_recieve,
        ),
        DashboardModel(
            id = "005",
            label = "History",
            iconRes = drawable.ic_history,
        )
    )
    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(230.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(size = 16.dp)

            )
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(

                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start



                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(drawable.ic_wallet),contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Your Wallet Balance", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(drawable.ic_dollar),contentDescription = null, modifier = Modifier.size(35.dp), tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("24562.00", overflow = TextOverflow.Ellipsis, fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(painter = painterResource(drawable.ic_qr,),contentDescription = null, tint = Color.White, modifier = Modifier.size(56.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in items){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .background(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer)
                                .size(64.dp)

                            ,
                            contentAlignment = Alignment.Center
                        ){
                            IconButton(onClick = {
                                println("======>this is $i")
                            }) {
                                Icon(painter = painterResource(i.iconRes),contentDescription = null, modifier = Modifier.size(30.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(i.label, color = Color.White, fontSize = 15.sp)


                    }
                }
            }
        }
    }
}