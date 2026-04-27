package kh.com.sela.android.topbartype.Util

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingContent(){
    Dialog(

        onDismissRequest = { }
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(50.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

object LoadingUtil {
    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    var isLoading = _isLoading
    fun showLoading(){
        _isLoading.value=true
    }

    fun hideLoading(){
        _isLoading.value=false
    }
}