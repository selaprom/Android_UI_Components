package kh.com.sela.android.topbartype.domain.usecase.local

import kh.com.sela.android.topbartype.data.base.BaseUiState
import kh.com.sela.android.topbartype.domain.BaseUseCase
import kh.com.sela.android.topbartype.domain.model.TaskModel
import kh.com.sela.android.topbartype.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) :
    BaseUseCase<TaskModel, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: TaskModel): Flow<BaseUiState<Unit>> {
        return flow {
            taskRepository.updateTask(params)
            emit(BaseUiState.Success(Unit))

    }
    }


}