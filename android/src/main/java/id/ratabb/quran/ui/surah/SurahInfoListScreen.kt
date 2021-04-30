package id.ratabb.quran.ui.surah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.ratabb.quran.ui.common.NumberArabic
import id.ratabb.quran.ui.common.TextArabic
import items.entity.SurahInfo

@Composable
fun SurahInfoListScreen(vm: SurahInfoViewModel, setNumSurah: (Int) -> Unit) {
    val state = vm.surahData.observeAsState(emptyList())
    SurahInfoGrid(state.value) { surahInfo -> setNumSurah(surahInfo.number) }
}

@Composable
fun SurahInfoGrid(
    data: List<SurahInfo>,
    onSurahInfoClick: (SurahInfo) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data) { surahInfo ->
            Card(
                modifier = Modifier
                    .padding(4.dp).padding(bottom = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onSurahInfoClick(surahInfo) },
                elevation = 8.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.primary)
            ) {
                SurahInfoItem(surahInfo, Modifier.fillMaxWidth().padding(8.dp))
            }
        }
    }
}

@Composable
fun SurahInfoItem(surah: SurahInfo, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth().weight(1F)
        ) {
            TextArabic(
                text = surah.textArabic,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(surah.textEnglish)
            Divider(Modifier.height(1.dp), Color.LightGray)
            Text(surah.textIndo)
            Spacer(Modifier.height(2.dp))
        }
        Surface(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(
                topStart = 4.dp,
                topEnd = 4.dp,
                bottomEnd = 4.dp,
                bottomStart = 16.dp
            ),
            color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.35F)
        ) {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                NumberArabic(
                    number = surah.number,
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
