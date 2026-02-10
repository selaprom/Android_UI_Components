import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.sela.android.topbartype.R.drawable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(){
    TopAppBar(

        title = {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.Start) {
                Text("Hello MaMa", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Text("WelcomeBack", color = MaterialTheme.colorScheme.secondary, fontSize = 17.sp)
            }
        },


        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        navigationIcon = {
            IconButton(onClick = {

            }, modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)

            ) {
                Icon(painter = painterResource(drawable.ic_profile),contentDescription = null, modifier = Modifier.size(30.dp), tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = {},
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(height = 25.dp, width = 60.dp)
                    .background(color = Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(30.dp)
                    )
                    
                ) {
                Icon(Icons.Default.Notifications,contentDescription = null)
            }
        }

    )
}