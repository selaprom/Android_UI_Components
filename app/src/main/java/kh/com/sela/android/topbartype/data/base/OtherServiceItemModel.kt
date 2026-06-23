package kh.com.sela.android.topbartype.data.base

data class OtherServiceItemModel(
    val id: String,
    val label:String,
    val iconRes:Int,
    val isEnabled: Boolean =true,
    val iconColorRes:Int,
    val backgroundColorRes:Int,
)
