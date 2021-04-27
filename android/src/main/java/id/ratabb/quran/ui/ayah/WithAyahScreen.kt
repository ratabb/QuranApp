package id.ratabb.quran.ui.ayah

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import id.ratabb.quran.ui.common.TextArabic
import items.entity.AyahEntity
import items.entity.SurahWithAyah

@Composable
fun WithAyahScreen(numberSurah: Int, vm: WithAyahViewModel) {
    vm.setNumberSurah(numberSurah)
    val surahWithAyah: SurahWithAyah = vm.withAyahData.observeAsState().value ?: return
    val basmallah: AyahEntity = vm.basmalah.observeAsState().value
        ?: throw IllegalStateException("Can't load basmallah")
    WithAyahContent(surahWithAyah, basmallah)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AyahEntityGrid(data: List<AyahEntity>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp),
        cells = GridCells.Fixed(1),
        content = { items(data.size) { AyahEntityItem(data[it]) } }
    )
}

@Composable
fun AyahEntityItem(ayahEntity: AyahEntity) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.large),
        elevation = 8.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Column {
            Row {
                TextArabic(
                    text = ayahEntity.textArabic,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth().weight(1F)
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = { setShowDialog(true) }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = ayahEntity.textEnglish!!,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
                fontWeight = FontWeight.Normal
            )
        }
    }
    MoreAyahEntity(showDialog, ayahEntity) {
        setShowDialog(false)
    }
}

@Composable
fun MoreAyahEntity(
    isShow: Boolean,
    ayahEntity: AyahEntity,
    onDismiss: () -> Unit
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            title = {
                TextArabic(
                    text = ayahEntity.textArabic,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold
                )
            },
            text = {
                Text(
                    text = ayahEntity.transIndo!!,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.body2
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(android.R.string.ok))
                }
            }
        )
    }
}
