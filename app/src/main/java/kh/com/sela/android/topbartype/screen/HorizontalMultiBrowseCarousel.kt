package kh.com.sela.android.topbartype.screen

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HorizontalMultiBrowseCarousels(){

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )
    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.imag_1, "cupcake"),
            CarouselItem(1, R.drawable.imag_2, "donut"),
            CarouselItem(2, R.drawable.imag_3, "eclair"),
            CarouselItem(3, R.drawable.imag_4, "froyo"),
            CarouselItem(4, R.drawable.imag_5, "gingerbread"),
            CarouselItem(5, R.drawable.imag_6, "gingerbread")
        )
    }
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
        }
    ){padding->
        Column(
            Modifier.padding(padding).padding(10.dp)
        ) {
            Box(
                modifier = Modifier.height(270.dp).fillMaxWidth().background(color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(15.dp))
            ) {
                Column() {
                    HorizontalMultiBrowseCarousel(
                        itemSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(10.dp),
                        state = rememberCarouselState() { items.count()},
                        preferredItemWidth = 200.dp

                    ) {index->
                        val item=items[index]

                        Box() {
                            Image(

                                modifier = Modifier.height(205.dp).maskClip(MaterialTheme.shapes.extraLarge),
                                painter = painterResource(id =item.imageResId ),
                                contentDescription = item.contentDescription,
                                contentScale = ContentScale.Crop
                            )
                            Text(item.contentDescription, modifier = Modifier.align(Alignment.BottomStart).padding(12.dp), color = Color.White, fontSize = 20.sp)
                        }

                    }
                    Text("Popular Foods", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreView(){
    HorizontalMultiBrowseCarousels()
}