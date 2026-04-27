package kh.com.sela.android.topbartype.model.base

sealed class BaseUiState<out T> {
    data object None: BaseUiState<Nothing>()
    data object Loading: BaseUiState<Nothing>()
    data class Success<out T>(val data:T): BaseUiState<T>()
    data class Error(val code: Int,val  message: String): BaseUiState<Nothing>()
    data class ErrorException(val message: String,val throwable: Throwable?=null): BaseUiState<Nothing>()


}