package kh.com.sela.android.topbartype.domain.repository

import kh.com.sela.android.topbartype.domain.model.TaskModel

interface TaskRepository {
    suspend fun getTasks(): List<TaskModel>
    suspend fun createTask(task: TaskModel): TaskModel
    suspend fun updateTask(task: TaskModel): TaskModel
    suspend fun deleteTask(taskId: String): TaskModel
    suspend fun getTaskById(taskId: String): TaskModel?
    suspend fun getTaskByTitle(title: String): List<TaskModel>
    suspend fun getTaskByStatus(status: String): List<TaskModel>

}