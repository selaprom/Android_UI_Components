package kh.com.sela.android.topbartype.di.imp

import kh.com.sela.android.topbartype.di.local.dao.TaskDao
import kh.com.sela.android.topbartype.di.local.entity.toTaskModel
import kh.com.sela.android.topbartype.di.local.entity.toTaskModelList
import kh.com.sela.android.topbartype.domain.model.TaskModel
import kh.com.sela.android.topbartype.domain.model.toTask
import kh.com.sela.android.topbartype.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun getTasks(): List<TaskModel> {
        return taskDao.getTaskLists().toTaskModelList()
    }

    override suspend fun createTask(task: TaskModel): TaskModel {
        val taskId = taskDao.createTask(task.toTask())
        return task.copy(taskId = taskId)
    }

    override suspend fun updateTask(task: TaskModel): TaskModel {
        taskDao.updateTask(task.toTask())
        return task
    }

    override suspend fun deleteTask(taskId: String): TaskModel {
        val task = taskDao.getTaskById(taskId) ?: throw Exception("Task not found")
        taskDao.deleteTask(taskId)
        return task.toTaskModel()
    }

    override suspend fun getTaskById(taskId: String): TaskModel? {
        return taskDao.getTaskById(taskId)?.toTaskModel()
    }

    override suspend fun getTaskByTitle(title: String): List<TaskModel> {
        return taskDao.getTaskByTitle(title).toTaskModelList()
    }

    override suspend fun getTaskByStatus(status: String): List<TaskModel> {
        return taskDao.getTaskByStatus(status).toTaskModelList()
    }

}