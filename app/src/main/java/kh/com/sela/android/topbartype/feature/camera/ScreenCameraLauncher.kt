package kh.com.sela.android.topbartype.feature.camera

import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.FileUtil

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCameraLauncher(
    onBack: () -> Unit = {},
) {

    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            imageBitmap = bitmap.asImageBitmap()
            val file = FileUtil.saveBitmapToCache(context, bitmap)
            if (file != null) {
                Toast.makeText(context, "Image Save To Cache Successfully", Toast.LENGTH_SHORT)
                    .show()
            }
            val uri = FileUtil.saveImageToGallery(context, bitmap)
            if (uri != null) {
                Toast.makeText(context, "Image Save To Gallery Successfully", Toast.LENGTH_SHORT)
                    .show()
            }
            val uri_download = FileUtil.saveBitmapToDownloads(context, bitmap)
            if (uri_download != null) {
                Toast.makeText(context, "Image Save To Downloads Successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(context, "Failed To Capture Image", Toast.LENGTH_SHORT).show()
        }


    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        } else {

            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }

    }

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            cameraLauncher.launch()
        } else {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }


    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {

            TopAppBar(
                title = { Text("Camera Take Picture") },
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

                )


        },
        bottomBar = {
            Button(

                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    openCamera()
                }) {
                Text("Launch Camera")
            }
        }
    ) { paddingvalue ->
        Column(
            modifier = Modifier

                .fillMaxSize()
                .padding(paddingvalue)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(20.dp))
                    .shadow(8.dp, RoundedCornerShape(20.dp))
            ) {
                imageBitmap?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

        }
    }

}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun PreviewScreenCameraLauncher() {
    ScreenCameraLauncher()
}