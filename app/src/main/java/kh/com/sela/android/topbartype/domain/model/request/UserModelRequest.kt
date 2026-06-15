package kh.com.sela.android.topbartype.domain.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModelRequest(
    @SerialName(value = "fullname")
    val fullname: String,
    @SerialName(value = "email")
    val email: String
    )
