package kh.com.sela.android.topbartype.feature.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.LoadingUtil
import kh.com.sela.android.topbartype.feature.notification.NotificationVM
import kh.com.sela.android.topbartype.model.base.BaseUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNotificationDetail(
    id: String,
    notification: NotificationVM = viewModel(),
    onBack: () -> Unit = {}
) {
    val notificationDetailUiState by notification.notificationDetailUiState.collectAsStateWithLifecycle()
    var title by remember { mutableStateOf("") }

    // Only trigger the API call once
    LaunchedEffect(key1 = Unit) {
        notification.getNotificationDetail(id)
    }

    // React to state changes separately
    LaunchedEffect(key1 = notificationDetailUiState) {
        when (val state = notificationDetailUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()
            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                title = state.data.title
            }
            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                println(state.message)
            }
            else -> {}
        }
    }

    when (val state = notificationDetailUiState) {
        is BaseUiState.Success -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_arrow_back),
                                    contentDescription = "Back"
                                )
                            }
                        },
                        title = { Text(title) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            ) { paddingValues ->

                when (state) {
                    is BaseUiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is BaseUiState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = state.message)
                        }
                    }

                    is BaseUiState.Success -> {
                        val data = state.data

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .padding(16.dp)
                        ) {



                            Spacer(modifier = Modifier.height(8.dp))

                            // Time
                            Text(
                                text = formatTime(data.timestamp),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Message Card
                            Card (
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = data.message,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Optional: Type Badge
                            Text(
                                text = data.type.name,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
        else -> {}
    }
}
