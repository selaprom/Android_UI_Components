package kh.com.sela.android.topbartype.feature.roomdatabase.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo


data class TaskOption(val key: String, val label: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskActionBottomsheet(
    onDelete: () -> Unit = {},
    onEdit: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = { /*TODO*/ },

        ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
          //  verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
                    .clickable{
                        onEdit()

                    }
                    .background(color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Text("Edit", color = MaterialTheme.colorScheme.onErrorContainer)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
                    .clickable{
                        onDelete()
                    }
                    .background(color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(size = 16.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Text("Delete", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun TaskActionBottomsheetPreview() {


    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) { }
    }
    TaskActionBottomsheet()
}