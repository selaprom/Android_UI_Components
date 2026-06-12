package kh.com.sela.android.topbartype.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateResponse(
    @SerialName("message")
    val message: String,
    @SerialName("updated_rows")
    val updatedRows: Int? = null
)