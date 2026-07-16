package kh.com.sela.android.topbartype.domain.repository

import kh.com.sela.android.topbartype.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
     fun getTasks(): Flow<List<TaskModel>>
    suspend fun createTask(task: TaskModel): TaskModel
    suspend fun updateTask(task: TaskModel): TaskModel
    suspend fun deleteTask(taskId: Long): TaskModel
    suspend fun getTaskById(taskId: String): TaskModel?
    suspend fun getTaskByTitle(title: String): List<TaskModel>
    suspend fun getTaskByStatus(status: String): List<TaskModel>

}