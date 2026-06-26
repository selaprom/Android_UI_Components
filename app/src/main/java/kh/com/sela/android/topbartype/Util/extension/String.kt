import kh.com.sela.android.topbartype.common.ValueYN

fun String.isYes(): Boolean{
    return this.uppercase()== ValueYN.YES.value
}
fun String.isNo(): Boolean{
    return this.uppercase()== ValueYN.NO.value
}

fun String.toValueYN(): ValueYN{
    return if (this.isYes()) ValueYN.YES else ValueYN.NO

}