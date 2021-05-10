package id.ratabb.quran.ui.ayah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import id.ratabb.quran.ui.common.ExpandableContent
import id.ratabb.quran.ui.common.NumberArabic
import id.ratabb.quran.ui.common.TextArabic
import items.entity.AyahEntity
import items.entity.SurahWithAyah

@Composable
fun AyahScreen(args: AyahScreenArgs) {
    val vm: AyahViewModel = hiltNavGraphViewModel()
    vm.setSurahAyah(args.numSurah, args.indexAyah)
    val surahWithAyah = vm.withAyahData.observeAsState().value
    val basmallah = vm.basmalah.observeAsState().value
    val indexAyah = vm.indexAyahData.observeAsState(0).value
    if (surahWithAyah != null && basmallah != null)
        AyahContent(surahWithAyah, indexAyah, basmallah)
}

@Composable
fun AyahContent(
    surahWithAyah: SurahWithAyah,
    indexAyah: Int,
    basmallah: AyahEntity
) {
    Column {
        TextArabic(
            text = surahWithAyah.surahEntity.textArabicLong!!,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))
        TextArabic(
            text = basmallah.textArabic,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
        AyahEntityGrid(surahWithAyah.ayahEntity, indexAyah)
    }
}

@Composable
fun AyahEntityGrid(data: List<AyahEntity>, indexAyah: Int) {
    LazyColumn(
        state = rememberLazyListState(indexAyah),
        modifier = Modifier.padding(12.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data, AyahEntity::hashCode) { AyahEntityItem(it) }
    }
}

@Composable
fun AyahEntityItem(ayahEntity: AyahEntity) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.medium),
        elevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Row {
                TextArabic(
                    modifier = Modifier.weight(1F).padding(horizontal = 4.dp),
                    text = ayahEntity.textArabic,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold
                )
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.35F)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        NumberArabic(
                            number = ayahEntity.numberInSurah,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Divider()
            // Text transliterasi in English
            ExpandableContent("Transliterasi", true) {
                Text(
                    text = ayahEntity.textEnglish!!,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
            }
            Divider()
            // Text translation in Bahasa Indonesia
            ExpandableContent("Translation") {
                Text(
                    text = ayahEntity.transIndo!!,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
            }
            Divider()
            // Text tafsir in Bahasa Indonesia
            ExpandableContent("Tafsir") {
                Text(
                    text = ayahEntity.tafsirShort!!,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
