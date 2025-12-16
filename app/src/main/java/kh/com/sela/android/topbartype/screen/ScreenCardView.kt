package kh.com.sela.android.topbartype.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevetionCard(){
    Scaffold(
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

                    title = {Text("ElevetionCard", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,color = Color.Black, modifier = Modifier.fillMaxWidth())},)
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
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            elevation = CardDefaults.elevatedCardElevation(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(size = 15.dp),

        ) {
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center

            ){
                Text("Hello World", fontSize = 25.sp)
            }
        }
    }
    }
}
@Preview(showBackground = true)
@Composable
fun ElevetionCardPreviews(){
    ElevetionCard()
}



