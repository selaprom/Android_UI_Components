package kh.com.pheaktra.developer.basic.jetpack.compse.weekend.utils.extension

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.Uri
import android.provider.Settings
import kh.com.sela.android.topbartype.common.ConnectionState
import kh.com.sela.android.topbartype.domain.model.hardware.ScreenInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

fun Context.shareImageUri(
    imageUri: Uri,
    chooserTitle: String = "Share image"
) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, imageUri)
        putExtra(Intent.EXTRA_TEXT, chooserTitle)
        clipData = ClipData.newUri(
            contentResolver,
            "Shared image",
            imageUri
        )
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    startActivity(
        Intent.createChooser(shareIntent, chooserTitle)
    )
}

fun Context.observeConnectivityAsFlow(): Flow<ConnectionState> = callbackFlow {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            trySend(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            trySend(ConnectionState.Unavailable)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val hasInternet = networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
            val validated = networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            )
            trySend(if (hasInternet && validated) ConnectionState.Available else ConnectionState.Unavailable)
        }
    }

    val request = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    // Emit current state immediately
    val activeNetwork = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    val initialState = if (capabilities != null &&
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    ) ConnectionState.Available else ConnectionState.Unavailable
    trySend(initialState)

    connectivityManager.registerNetworkCallback(request, callback)

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}.distinctUntilChanged()

fun Context.getScreenInfo(): ScreenInfo {
    val metrics = this.resources.displayMetrics

    val widthPx = metrics.widthPixels
    val heightPx = metrics.heightPixels

    val widthDp = (widthPx / metrics.density).toInt()
    val heightDp = (heightPx / metrics.density).toInt()

    val widthInches = widthPx / metrics.xdpi
    val heightInches = heightPx / metrics.ydpi
    val physicalSize = kotlin.math.sqrt(
        widthInches * widthInches + heightInches * heightInches
    )

    val brightness = Settings.System.getInt(
        this.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS,
        0
    )

    val brightnessMode = Settings.System.getInt(
        this.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS_MODE,
        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
    )

    val gcd = gcd(widthPx, heightPx)

    return ScreenInfo(
        widthPx = widthPx,
        heightPx = heightPx,
        widthDp = widthDp,
        heightDp = heightDp,
        density = metrics.density,
        resolution = "$widthPx x $heightPx",
        aspectRatio = "${widthPx / gcd}:${heightPx / gcd}",
        physicalSizeInches = physicalSize.toDouble(),
        brightness = brightness,
        isAutoBrightness = brightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC,
        minBrightness = 0,
        maxBrightness = 255
    )
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}