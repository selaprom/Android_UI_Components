package kh.com.sela.android.topbartype.feature.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(){
    var homeVM = HomeVM()
    val message by homeVM.message.collectAsStateWithLifecycle()
    Scaffold(
            modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
        Button({
                homeVM.getMessage()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Get Message")
        }
    },

        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                title = {
                    Text("MyApp")
                }
            )
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Text(message)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun ScreenHomePreview(){
    TopBarTypeTheme() {
        ScreenHome()
    }
}