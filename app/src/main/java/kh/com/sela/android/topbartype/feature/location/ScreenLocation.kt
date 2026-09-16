package kh.com.sela.android.topbartype.feature.location

import TopAppBar
import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.location.LocationServices
import kh.com.sela.android.topbartype.R

import kh.com.sela.android.topbartype.navigation.Button
import kh.com.sela.android.topbartype.navigation.TopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ScreenLocation(
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    var latitude by remember {
        mutableDoubleStateOf(0.0)
    }

    var longitude by remember {
        mutableDoubleStateOf(0.0)
    }

    var showPermissionDialog by remember {
        mutableStateOf(false)
    }
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //call get location from hareware
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                    } else {
                        Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            //request permission
            permissionState.launchPermissionRequest()
        }
    }
    fun openGoogleMaps() {
        val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude".toUri()
        val mapIntent = Intent(Intent.ACTION_VIEW, geoUri).apply {
            setPackage("com.google.android.apps.maps")

        }
        try {
            context.startActivity(mapIntent)

        }catch (_: ActivityNotFoundException){
            //Google maps is not install
            //open the location using any available app application
            val fallbackIntent = Intent(Intent.ACTION_VIEW, geoUri)
            context.startActivity(fallbackIntent)
        }
    }
    LaunchedEffect(key1 = permissionState.status) {
        if (permissionState.status.isGranted) {
            // Permission has been granted
            getLocation()
        } else  {
            showPermissionDialog = true
        }

    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),

        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                title = {
                    Text("Location")
                },
                navigationIcon = {
                    IconButton(onClick = {

                        onBackClick()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = null
                        )
                    }
                },


                )

        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ){

                Button(
                    modifier = Modifier

                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        //check permission is granted
                        getLocation()
                    },
                ) {
                    Text("Get Location")
                }
                Button(
                    modifier = Modifier

                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        //Open Google Maps
                        openGoogleMaps()

                    },
                ) {
                    Text("Open Google Maps")
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Latitude: $latitude")
            Text("Longitude: $longitude")

            println("location===> : $latitude , $longitude")

        }
        if (showPermissionDialog) {
            AlertDialogPermissionDenied(
                onDismiss = {showPermissionDialog=false},
                message = "Please enable location permission to get your location",
                title = "Permission Denied"
            )
        }
    }

}

@Composable
fun AlertDialogPermissionDenied(
    onDismiss: () -> Unit,
    message: String,
    title: String,
){
    AlertDialog(
        shape = RoundedCornerShape(size = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),

                contentDescription = null,
                modifier = Modifier.size(56.dp)

                )
        },
        title = {
            Text(
                text = title,


            )


        },
        text = {
            Text(
                text = message
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton (onClick = {
                onDismiss()
            }) {
                Text(text = "Close")
            }
        },

    )
}



@Composable
@Preview(showBackground = true)
fun ShowPreview() {
    ScreenLocation(onBackClick = {})
}
