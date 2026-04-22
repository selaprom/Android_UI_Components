package kh.com.sela.android.topbartype.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(){
    var homeVM = HomeVM()
    val message by homeVM.message.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        homeVM.getMessage()
    }
    Scaffold(
            modifier = Modifier.navigationBarsPadding(),
        bottomBar = {

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {
            items(message.size){
                Row(
                    modifier = Modifier.height(56.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(

                         text =   message[it])
                }
                HorizontalDivider()

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