package kh.com.sela.android.topbartype.feature.roomdatabase

import android.R.attr.navigationIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.common.ValueYN
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.domain.model.TaskModel
import kh.com.sela.android.topbartype.domain.model.isComplete
import kh.com.sela.android.topbartype.feature.roomdatabase.bottomsheet.TaskActionBottomsheet
import kh.com.sela.android.topbartype.navigation.RadioButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRoomDatabase(
    roomVM: RoomVM = viewModel(),
    onBackClick: () -> Unit,
    onCreateTask: () -> Unit,
    onEditTask: (TaskModel) -> Unit
) {
var isLongPress by remember { mutableStateOf(false) }
    val taskListUiState by roomVM.taskListUiState.collectAsStateWithLifecycle()
var taskIndex by retain {mutableStateOf<TaskModel?>(null)}
    Scaffold(

        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    onCreateTask()
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                },
                text = {
                    Text("Create Task")
                }
            )
        },
        topBar = {


            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier,

                title = {
                    Text(
                        "Room Database", fontSize = 25.sp, style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold

                    )
                },


                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            modifier = Modifier.size(30.dp),
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
                }

            )


        },



        ) { paddingValues ->
        if (taskListUiState is BaseUiState.Success) {
            val taskList = (taskListUiState as BaseUiState.Success<List<TaskModel>>).data
            if (taskList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                ) {
                    items(taskList.size) { index ->
                        val task = taskList[index]
                        TaskItem(
                            task = task, modifier = Modifier
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp)
                                .height(64.dp)
                                ,
                            onClick = { task ->
                                onEditTask(task)
                            },
                            onLongPress = {
                                taskIndex = it
                                isLongPress = true

                            },
                            onClickRadioButton = {
                                val task= it.copy(taskCompleteYN = if (it.taskCompleteYN == ValueYN.YES) ValueYN.NO else ValueYN.YES)
                                roomVM.updateTask(task)

                            }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No Task Data", color = Color.Blue)
                }
            }

        }



    }
    if (isLongPress) {
        TaskActionBottomsheet(
            onDelete = {

            isLongPress = false
            taskIndex?.let {
                roomVM.deleteTask(it)
            }

        }, onEdit = {
            isLongPress = false
            taskIndex?.let {
                roomVM.updateTask(it)
            }
        })
    }
}


@Composable
fun TaskItem(modifier: Modifier = Modifier,
             task: TaskModel,
             onClick: (TaskModel) -> Unit,
             onLongPress:(TaskModel)->Unit = {},
             onClickRadioButton:(TaskModel)->Unit = {})
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .then(modifier)
            .height(64.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick.invoke(task) },
                onLongClick = {
                    onLongPress.invoke(task)

                }
            )


            .border(
                width = 2.dp,
                shape = RoundedCornerShape(16.dp),
                color = if (task.isComplete()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,

                )



        ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),

                text =
                    "${task.taskName}",
                color = Color.DarkGray,

                maxLines = 1,

                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                textDecoration = if (task.isComplete()) TextDecoration.LineThrough else TextDecoration.None,

                )
            Text("${task.taskDescription}", color = Color.DarkGray)
        }
        RadioButton(
            selected = task.taskCompleteYN == ValueYN.YES,
            onClick = { onClickRadioButton.invoke(task) }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TaskItemPreview() {
    TaskItem(
        task = TaskModel(
            taskId = 1,
            taskName = "Task 1",
            taskDescription = "Hello kon papa",

            taskCompleteYN = ValueYN.YES,
        ),
        onClick = {}

        )
}

@Composable
@Preview(showBackground = true)
fun ScreenRoomDatabasePreview() {
    ScreenRoomDatabase(onBackClick = {}, onCreateTask = {}, onEditTask = {})
}