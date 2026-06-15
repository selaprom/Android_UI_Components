package kh.com.sela.android.topbartype.domain.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModelResponse(
    @SerialName(value = "id")
    val id: Int,
    @SerialName(value = "fullname")
    val fullname: String,
    @SerialName(value = "email")
    val email: String

    )
