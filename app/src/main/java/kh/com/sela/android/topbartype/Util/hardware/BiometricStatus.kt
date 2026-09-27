package kh.com.sela.android.topbartype.Util.hardware

sealed interface BiometricStatus {

    data object Available : BiometricStatus

    data object NoHardware : BiometricStatus

    data object HardwareUnavailable : BiometricStatus

    data object NoneEnrolled : BiometricStatus

    data class Unsupported(
        val code: Int
    ) : BiometricStatus
}