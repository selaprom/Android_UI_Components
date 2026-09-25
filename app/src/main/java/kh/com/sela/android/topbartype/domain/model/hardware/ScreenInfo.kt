package kh.com.sela.android.topbartype.domain.model.hardware

data class ScreenInfo(
    val widthPx: Int,
    val heightPx: Int,
    val widthDp: Int,
    val heightDp: Int,
    val density: Float,
    val resolution: String,
    val aspectRatio: String,
    val physicalSizeInches: Double,
    val brightness: Int,
    val isAutoBrightness: Boolean,
    val minBrightness: Int,
    val maxBrightness: Int
)