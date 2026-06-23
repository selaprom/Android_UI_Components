package kh.com.sela.android.topbartype.feature.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.sela.android.topbartype.data.base.CardModel
import kh.com.sela.android.topbartype.data.base.BaseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardVM (private val cardRepository: CardRepository= CardRepository()): ViewModel() {
    private var _cardListUiState : MutableStateFlow<BaseUiState<List<CardModel>>> = MutableStateFlow<BaseUiState<List<CardModel>>>(
        BaseUiState.None)
    var cardListUiState = _cardListUiState.asStateFlow()
    fun getCardList(){
        viewModelScope.launch {
            _cardListUiState.emit(BaseUiState.Loading)
            delay(timeMillis = 1500)
            cardRepository.getMessage().collect { data->
                _cardListUiState.emit(BaseUiState.Success(data))
            }
        }
    }
    init {
        getCardList()
    }
}