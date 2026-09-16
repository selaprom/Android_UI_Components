package kh.com.sela.android.topbartype.feature.androidXCamera

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kh.com.sela.android.topbartype.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenPreviewImage(
    onBack: () -> Unit = {},
    imageUri:Any
){
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Image Preview")
                },
                modifier = Modifier,
                navigationIcon = {
                    IconButton({
                        onBack()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton({}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = null
                        )
                    }
                },


                )
        }, bottomBar =
            {
                FilledTonalButton(
                    modifier = Modifier

                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = {

                    },


                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text("Share")
                }

            }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {


                AsyncImage(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ,
                    model = imageUri

                    ,
                    contentDescription = "Selected Image",

                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}