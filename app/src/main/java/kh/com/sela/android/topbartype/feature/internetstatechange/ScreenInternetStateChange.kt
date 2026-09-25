package kh.com.sela.android.topbartype.feature.internetstatechange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.common.ConnectionState
import kh.com.sela.android.topbartype.service.connectivity.connectivityState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenInternetStateChange(onBack: () -> Unit = {}) {

    val scope = rememberCoroutineScope()

    val connectionState by connectivityState()
    val isConnected = connectionState === ConnectionState.Available
    val sheetState = rememberModalBottomSheetState (
        skipPartiallyExpanded = true
    )
    LaunchedEffect(key1 = isConnected) {
        if (!isConnected) {
            sheetState.show()
        } else {
            sheetState.hide()
        }

    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Internet State") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Internet State:")
                Text(
                    text = if (isConnected) "Connected" else "Disconnected",
                    modifier = Modifier.padding(start = 8.dp)
                )

            }

        }
        if (!isConnected) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }
                    // Prevent dismiss while still disconnected, or allow and let it reappear
                },
                sheetState = sheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                NoInternetSheetContent(
                    onRetry = {
                        // Optionally trigger a manual re-check or just let the flow re-emit
                    }
                )
            }
        }
    }
}

