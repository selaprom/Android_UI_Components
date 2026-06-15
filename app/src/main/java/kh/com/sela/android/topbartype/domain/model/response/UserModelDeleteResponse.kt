package kh.com.sela.android.topbartype.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModelDeleteResponse (
    @SerialName("message")
    val message: String,
    @SerialName("updated_rows")
    val updatedRows: Int? = null
)