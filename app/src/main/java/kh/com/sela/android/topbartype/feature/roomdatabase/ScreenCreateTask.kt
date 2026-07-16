package kh.com.sela.android.topbartype.feature.roomdatabase

import android.R.attr.navigationIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Scaffold
import isYes
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.common.ValueYN
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.domain.model.TaskModel
import kh.com.sela.android.topbartype.navigation.RadioButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCreateTask(
    taskData: TaskModel? = null,
    roomVM: RoomVM = viewModel(),
    onBackClick: () -> Unit = {},
) {

    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current
    var taskCompleteYN by remember { mutableStateOf(ValueYN.NO) }
    val isKeyboardVisible = WindowInsets.ime.getBottom( density )>0
    fun onSave() {
        val task = TaskModel(
            taskId = taskData?.taskId ?: 0L,
            taskName = taskName.trim(),
            taskDescription = taskDescription.trim(),
            taskCompleteYN = taskCompleteYN
        )
        //save to room database
        if (taskData != null) {
            roomVM.updateTask(task)

        } else {
            roomVM.createTask(task)
        }
        onBackClick()

    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    LaunchedEffect(key1 = taskData) {
        if (taskData != null) {
            taskName = taskData.taskName
            taskDescription = taskData.taskDescription
            taskCompleteYN = taskData.taskCompleteYN

        }
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                enabled = true,
                onClick = {
                    keyboardController?.hide()
                },
                indication = null,
                interactionSource = null
            )
            .navigationBarsPadding()
            .imePadding(),
        bottomBar = {
            Button(
                onClick = {
                    onSave()

                },
                modifier = Modifier
                    .padding(horizontal = if (!isKeyboardVisible)16.dp else 0.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(if (!isKeyboardVisible) 16.dp else 0.dp))
            ) {
                Text(
                    if (taskData == null) "Create Task" else "Update Task",
                )

            }
        },


        topBar = {


            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier,

                title = {
                    Text(
                        "Create Task", style = TextStyle(fontSize = 22.sp)

                    )
                },


                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
                }

            )


        },


        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text(text = "Task Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .focusRequester(focusRequester),
            )

            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text(text = "Task Description") },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                minLines = 3
            )
            DropdownInput(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                label = "Task Completed",
                options = listOf(
                    DropDownItem(
                        label = "Yes",
                        value = ValueYN.YES
                    ),
                    DropDownItem(
                        label = "No",
                        value = ValueYN.NO
                    )
                ),
                selectedValue = if (taskCompleteYN.value.isYes()) "Yes" else "No",
                onValueSelected = {
                    taskCompleteYN = it
                }
            )


        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun DropdownInput(
    label: String,
    options: List<DropDownItem>,
    selectedValue: String,
    onValueSelected: (ValueYN) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    )
    {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = { },
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.label) },
                    onClick = {
                        onValueSelected(option.value)
                        expanded = false
                    }
                )
            }
        }

    }
}

data class DropDownItem(val label: String, val value: ValueYN)

@Composable
@Preview(showBackground = true)
fun DropdownInputPreview() {
    DropdownInput(
        label = "Task Completed",
        options = listOf(
            DropDownItem(
                label = "Yes",
                value = ValueYN.YES
            ),
            DropDownItem(
                label = "No",
                value = ValueYN.NO
            )
        ),
        selectedValue = "YES",
        onValueSelected = {}
    )
}


@Composable
@Preview(showBackground = true)
fun ScreenCreateTaskPreview() {
    ScreenCreateTask(

    )
}