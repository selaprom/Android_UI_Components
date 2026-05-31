package kh.com.sela.android.topbartype.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.LoadingUtil
import kh.com.sela.android.topbartype.model.base.BaseUiState
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHomes(
    homeVM: HomeVM = viewModel() ,
    onClickNotification: () -> Unit = {},
    onClickCard: () -> Unit = {},
    onClickComponent: (Any) -> Unit = {}
) {

    val messageUiState by homeVM.messageUiState.collectAsStateWithLifecycle()
    val componentListUiState by homeVM.componentList.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {

        homeVM.getComponentList()
    }

    LaunchedEffect(key1 = messageUiState, key2 = componentListUiState) {
        if (messageUiState is BaseUiState.Loading || componentListUiState is BaseUiState.Loading) {
            LoadingUtil.showLoading()
        } else {
            LoadingUtil.hideLoading()
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
                    Text("MyApp")
                }, actions = {
                    IconButton(onClick = onClickNotification) {
                        Icon(
                            painter = painterResource(R.drawable.ic_notification),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .clickable(onClick = onClickCard)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("CardView")
                }
                HorizontalDivider()
            }

            if (componentListUiState is BaseUiState.Success) {
                val components = (componentListUiState as BaseUiState.Success).data
                items(components.size) { index ->
                    val component = components[index]
                    Row(
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .clickable { onClickComponent(component.key) }
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = component.title)
                    }
                    HorizontalDivider()
                }
            }
        }

    }
}
@Composable
@Preview(showBackground = true)
fun ScreenHomePreview(){
    TopBarTypeTheme {
        ScreenHomes()
    }
}