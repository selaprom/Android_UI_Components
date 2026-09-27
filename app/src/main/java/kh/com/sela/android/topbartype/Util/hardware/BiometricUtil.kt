package kh.com.sela.android.topbartype.Util.hardware

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object BiometricUtil {

    private const val AUTHENTICATORS =
        BiometricManager.Authenticators.BIOMETRIC_STRONG

    fun checkAvailability(context: Context): BiometricStatus {
        val biometricManager = BiometricManager.from(context)

        return when (
            val result = biometricManager.canAuthenticate(AUTHENTICATORS)
        ) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                BiometricStatus.Available
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                BiometricStatus.NoHardware
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                BiometricStatus.HardwareUnavailable
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                BiometricStatus.NoneEnrolled
            }

            else -> {
                BiometricStatus.Unsupported(result)
            }
        }
    }

    fun authenticate(
        activity: FragmentActivity,
        title: String = "Fingerprint authentication",
        subtitle: String = "Confirm your identity to continue",
        negativeButtonText: String = "Cancel",
        onSuccess: () -> Unit,
        onError: (
            errorCode: Int,
            errorMessage: String
        ) -> Unit,
        onFailed: () -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)

        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(
                        errorCode,
                        errString
                    )

                    onError(
                        errorCode,
                        errString.toString()
                    )
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setAllowedAuthenticators(AUTHENTICATORS)
            .setNegativeButtonText(negativeButtonText)
            .setConfirmationRequired(false)
            .build()

        prompt.authenticate(promptInfo)
    }

    fun openBiometricEnrollment(context: Context) {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    AUTHENTICATORS
                )
            }
        } else {
            Intent(Settings.ACTION_SECURITY_SETTINGS)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}