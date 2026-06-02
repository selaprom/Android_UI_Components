package kh.com.sela.android.topbartype.feature.postnotification

import TopAppBar
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.navigation.TopAppBar
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenPostNotification(
    onBackScreen: () -> Unit = {}
) {
    val context = LocalContext.current
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if (!isGranted){
            val toast = Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    LaunchedEffect(Unit) {
        //Request permission
        notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post Notification") },
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_notification),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackScreen() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }


            )
        }
    ) { paddingvalue ->
        Column(modifier = Modifier.padding(paddingvalue)) {


        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScreenPostNotification() {

    TopBarTypeTheme{
        ScreenPostNotification()
    }

}
