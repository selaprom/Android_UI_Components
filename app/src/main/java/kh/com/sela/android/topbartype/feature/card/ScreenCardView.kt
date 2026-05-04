package kh.com.sela.android.topbartype.feature.card

import TopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.sela.android.topbartype.R
import kh.com.sela.android.topbartype.Util.LoadingUtil
import kh.com.sela.android.topbartype.model.CardModel
import kh.com.sela.android.topbartype.model.base.BaseUiState
import kh.com.sela.android.topbartype.screen.CenterTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCardView(
    cardVM: CardVM = CardVM(), onBack: () -> Unit = {}
) {
    val cardListUiState by cardVM.cardListUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = cardListUiState) {
        when (val state = cardListUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> LoadingUtil.hideLoading()
            is BaseUiState.Error -> {
                LoadingUtil.hideLoading()
                println("Error:${state.message}")
            }

            is BaseUiState.ErrorException -> {
                LoadingUtil.hideLoading()
                println("ErrorException: ${state.message}")
            }

            else -> {}
        }
    }
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text("ScreenCardView") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(R.drawable.ic_arrow_back),contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = cardListUiState) {
            is
            BaseUiState.Success -> {
                val cardList = state.data

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(cardList) { card ->
                        CardItem(card)
                    }
                }
            }

            else -> {}
        }
    }

}

@Composable
fun CardItem(card: CardModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = card.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = card.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}