package id.ratabb.quran.ui.ayah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.ratabb.quran.ui.common.TextArabic
import items.entity.AyahEntity
import items.entity.SurahWithAyah

@Composable
fun WithAyahScreen(vm: WithAyahViewModel) {
    val surahWithAyah = vm.withAyahData.observeAsState().value
    val basmallah = vm.basmalah.observeAsState().value
    if (surahWithAyah != null && basmallah != null) WithAyahContent(surahWithAyah, basmallah)
}

@Composable
fun WithAyahContent(
    surahWithAyah: SurahWithAyah,
    basmallah: AyahEntity
) {
    Column {
        TextArabic(
            text = surahWithAyah.surahEntity.textArabicLong!!,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))
        TextArabic(
            text = basmallah.textArabic,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        AyahEntityGrid(surahWithAyah.ayahEntity)
    }
}

@Composable
fun AyahEntityGrid(data: List<AyahEntity>) {
    LazyColumn(Modifier.padding(12.dp)) {
        items(data.size, key = { data[it].numberInSurah }) { AyahEntityItem(data[it]) }
    }
}

@Composable
fun AyahEntityItem(ayahEntity: AyahEntity) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.large),
        elevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            TextArabic(
                text = ayahEntity.textArabic,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold
            )
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
            // Text transliterasi in English
            Text(
                text = ayahEntity.textEnglish!!,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
            // Text translation in Bahasa Indonesia
            Text(
                text = ayahEntity.transIndo!!,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
