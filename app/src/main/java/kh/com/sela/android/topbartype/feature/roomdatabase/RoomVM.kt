package kh.com.sela.android.topbartype.feature.roomdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.domain.model.TaskModel
import kh.com.sela.android.topbartype.domain.repository.TaskRepository
import kh.com.sela.android.topbartype.domain.usecase.local.CreateTaskUseCase
import kh.com.sela.android.topbartype.domain.usecase.local.DeleteTaskUseCase
import kh.com.sela.android.topbartype.domain.usecase.local.GetTaskListUseCase
import kh.com.sela.android.topbartype.domain.usecase.local.UpdateTaskUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomVM @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {
    val taskListUiState: StateFlow<BaseUiState<List<TaskModel>>> = getTaskListUseCase.invoke(Unit)
        .stateIn(
            scope = viewModelScope,
            initialValue = BaseUiState.None,
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            createTaskUseCase.invoke(task).collect {
                println(it)
            }
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase.invoke(task).collect {
                println(it)
            }
        }

    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(task).collect {
                println(it)
            }
        }
    }

}


