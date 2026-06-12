package kh.com.sela.android.topbartype.feature.mediapicker

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.media3.common.MediaItem
import coil.compose.AsyncImage
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.feature.postnotification.sendNotification

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSelectedMultipleVideos(
    onBack: () -> Unit = {},
) {
    var listVideoUri by remember {
        mutableStateOf<List<Uri>?>(null)
    }
    val scrollState = rememberScrollState()

    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),

        ) { listUri ->
        if (listUri.isNotEmpty()) {
            listVideoUri = listUri
            println("=======>Video Url: $listVideoUri")


        } else {

        }

    }



    fun onPickMedia() {
        pickMedia.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
        )
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Selected Multiple Videos")
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
                Button(
                    modifier = Modifier

                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = {
                        onPickMedia()
                    },


                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text("Select Multiple Videos")
                }

            }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(state = scrollState)
            .padding(paddingValues)) {
            listVideoUri.let { it->
                it?.forEach { videoUri ->

                        Box(

                            modifier = Modifier
                                .padding(16.dp)

                                .wrapContentSize()
                                .fillMaxSize() // 👈 full screen
                                .background(MaterialTheme.colorScheme.surfaceVariant,),
                            contentAlignment = Alignment.Center
                        ) {
                            VideoPlayerScreen(
                                videoUri = videoUri,
                                modifier = Modifier.fillMaxSize() // 👈 important
                            )
                        }
                }

            }

        }

        }

}

@Preview(showBackground = true)
@Composable
fun PreviewScreenSelectedMultipleVideos() {
    ScreenSelectedMultipleVideos()
}