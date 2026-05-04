package kh.com.sela.android.topbartype.feature.card

import kh.com.sela.android.topbartype.model.CardModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepository {

    val cardList = listOf(
        CardModel("1", "Title 1", "Description for card 1"),
        CardModel("2", "Title 2", "Description for card 2"),
        CardModel("3", "Title 3", "Description for card 3"),
        CardModel("4", "Title 4", "Description for card 4"),
        CardModel("5", "Title 5", "Description for card 5"),
        CardModel("6", "Title 6", "Description for card 6"),
        CardModel("7", "Title 7", "Description for card 7"),
        CardModel("8", "Title 8", "Description for card 8"),
        CardModel("9", "Title 9", "Description for card 9"),
        CardModel("10", "Title 10", "Description for card 10"),
        CardModel("11", "Title 11", "Description for card 11"),
        CardModel("12", "Title 12", "Description for card 12"),
        CardModel("13", "Title 13", "Description for card 13"),
        CardModel("14", "Title 14", "Description for card 14"),
        CardModel("15", "Title 15", "Description for card 15"),
        CardModel("16", "Title 16", "Description for card 16"),
        CardModel("17", "Title 17", "Description for card 17"),
        CardModel("18", "Title 18", "Description for card 18"),
        CardModel("19", "Title 19", "Description for card 19"),
        CardModel("20", "Title 20", "Description for card 20")
    )

     suspend fun getMessage(): Flow<List<CardModel>> {
         delay(timeMillis = 1000)
        return flow {
            emit(cardList)
        }
    }
}