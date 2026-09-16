package kh.com.sela.android.topbartype.feature.androidXCamera

import android.Manifest
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kh.com.sela.android.topbartype.feature.chip.Preview
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScreenCameraView(
    onClose: () -> Unit,
    onImageCaptured: (Uri) -> Unit,
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }

    }
    if (cameraPermissionState.status.isGranted) {
        CameraContent(
            onClose = onClose,
            onImageCaptured = onImageCaptured
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {}) {
                Text("Allow Camera Permission")
            }

        }
    }


}

@Composable
fun CameraContent(
    onClose: () -> Unit,
    onImageCaptured: (Uri) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        if (LocalInspectionMode.current) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                contentAlignment = Alignment.Center
            ){
                Text("camera preview placeholder", color = Color.White)
            }
        }else{
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->

                    val previewView = PreviewView(ctx).apply {
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                    }

                    val cameraProviderFuture =
                        ProcessCameraProvider.getInstance(ctx)

                    cameraProviderFuture.addListener({

                        val cameraProvider = cameraProviderFuture.get()

                        val preview = Preview.Builder()
                            .build()
                            .also {
                                it.surfaceProvider = previewView.surfaceProvider
                            }
                        val capture = ImageCapture.Builder()
                            .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                            .build()

                        imageCapture = capture
                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            capture,
                            preview
                        )

                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },


            )
        }
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .systemBarsPadding()
                .padding(16.dp)
                .align(Alignment.TopStart)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    shape = CircleShape
                )
        ) {
            Icon(

                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Button(
            {

                captureImage(
                    context = context,
                    imageCapture = imageCapture,
                    onImageCaptured = onImageCaptured
                )
            },
            modifier = Modifier
                .padding(48.dp)
                .align(Alignment.BottomCenter)
                .size(78.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),

            ) {
            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Capture",
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

    }
}


private fun captureImage(
    context: Context,
    imageCapture: ImageCapture?,
    onImageCaptured: (Uri) -> Unit
) {
    val capture = imageCapture ?: return

    val photoFile = File(
        context.cacheDir,
        "camera_${System.currentTimeMillis()}.jpg"
    )

    val photoUri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        photoFile
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    capture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {

            override fun onImageSaved(
                outputFileResults: ImageCapture.OutputFileResults
            ) {
                onImageCaptured(photoUri)
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(
                    context,
                    exception.message ?: "Capture failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )
}
