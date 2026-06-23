package kh.com.sela.android.topbartype.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateRequest(
    @SerialName(value = "id")
    val id: Int? =null,
    @SerialName(value = "fullname")
    val fullname: String,
    @SerialName(value = "email")
    val email: String)
