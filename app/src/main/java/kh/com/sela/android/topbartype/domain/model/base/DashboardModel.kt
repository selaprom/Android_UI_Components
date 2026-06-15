package kh.com.sela.android.topbartype.domain.model.base

data class DashboardModel(
    val id: String,
    val label:String,
    val iconRes:Int,
    val isEnable: Boolean =true
)
