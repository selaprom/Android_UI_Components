package kh.com.sela.android.topbartype.di.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kh.com.sela.android.topbartype.common.TableName
import kh.com.sela.android.topbartype.domain.model.TaskModel
import toValueYN

@Entity(TableName.TASK)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val taskId:Long,
    @ColumnInfo("task_name") val taskName:String,
    @ColumnInfo("task_description") val taskDescription:String,
    @ColumnInfo("task_completed") val taskCompleted: String
)

fun Task.toTaskModel() : TaskModel {
    return TaskModel(
        taskId = this.taskId,
        taskName = taskName,
        taskDescription = taskDescription,
        taskCompleteYN = this.taskCompleted.toValueYN()
    )

}

fun List<Task>.toTaskModelList(): List<TaskModel> {
    return this.map { it.toTaskModel() }

}