package kh.com.sela.android.topbartype.feature.home

class HomeRipository {
    private  var messageList=List(100){index -> "this is index ${index+1}"}
    suspend fun getMessage(): List<String> {
        return messageList
    }
}