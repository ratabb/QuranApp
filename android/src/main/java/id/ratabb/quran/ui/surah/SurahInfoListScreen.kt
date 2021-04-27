package id.ratabb.quran.ui.surah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import id.ratabb.quran.ui.WITH_AYAH
import id.ratabb.quran.ui.common.TextArabic
import items.entity.SurahInfo

@Composable
fun SurahInfoListScreen(navController: NavController, vm: SurahInfoViewModel) {
    val state = vm.surahData.observeAsState(emptyList())
    SurahInfoGrid(state.value) { surahInfo ->
        navController.navigate("${WITH_AYAH}${surahInfo.number}")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurahInfoGrid(data: List<SurahInfo>, onSurahInfoClick: (SurahInfo) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp),
        cells = GridCells.Fixed(2),
        content = { items(data.size) { SurahInfoItem(data[it], onSurahInfoClick) } }
    )
}

@Composable
fun SurahInfoItem(surah: SurahInfo, onSurahInfoClick: (SurahInfo) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.medium)
        /*.clickable { onSurahInfoClick(surah) }*/,
        elevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column {
            Row {
                TextArabic(
                    text = surah.textArabic, //
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth().weight(1F)
                        .padding(8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = { onSurahInfoClick(surah) }) {
                    Icon(
                        imageVector = Icons.Filled.Launch,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = surah.textEnglish, //
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
