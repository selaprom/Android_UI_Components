package kh.com.sela.android.topbartype.domain.model

import isNo
import isYes

import kh.com.sela.android.topbartype.common.ValueYN
import kh.com.sela.android.topbartype.di.local.entity.Task
import kotlinx.serialization.Serializable

@Serializable
data class TaskModel(
    val taskId: Long,
    val taskName: String,
    val taskDescription: String,
    val taskCompleteYN: ValueYN
)

fun TaskModel.isComplete(): Boolean {
    return this.taskCompleteYN.value.isYes()
}
fun TaskModel.inComplete(): Boolean {
    return this.taskCompleteYN.value.isNo()
}

fun TaskModel.toTask():Task{
    return Task(
        taskId = this.taskId,
        taskName = this.taskName,
        taskDescription = this.taskDescription,
        taskCompleted = this.taskCompleteYN.value
    )

}