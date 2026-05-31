package kh.com.sela.android.topbartype.feature.postnotification

import TopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.navigation.TopAppBar
import kh.com.sela.android.topbartype.ui.theme.TopBarTypeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenPostNotification(

) {
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
                    IconButton(onClick = { /* Handle action */ }) {
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
