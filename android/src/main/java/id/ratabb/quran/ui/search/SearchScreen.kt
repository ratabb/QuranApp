package id.ratabb.quran.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import id.ratabb.quran.R
import id.ratabb.quran.ui.common.SearchTextField
import items.entity.AyahFtsView

@Composable
fun SearchScreen(vm: SearchViewModel, onAyahEntityClick: (AyahFtsView) -> Unit) {
    val state = vm.ayahEntities.observeAsState(emptyList())
    val names = vm.surahName.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }
    Column {
        SearchTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                vm.search(searchQuery.text)
            },
            hint = stringResource(R.string.search),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { vm.search(searchQuery.text) }),
            singleLine = true
        )
        LazyColumn(
            modifier = Modifier.padding(all = 8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.value) { entity ->
                Card(
                    modifier = Modifier
                        .padding(4.dp).padding(bottom = 8.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onAyahEntityClick(entity) },
                    elevation = 8.dp,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                ) {
                    AyahEntityItem(
                        entity,
                        names.value,
                        Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun AyahEntityItem(
    ftsView: AyahFtsView,
    nameList: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp)) {
        val numberSurah = ftsView.numberSurah
        Text(text = "QS $numberSurah:${ftsView.numberInSurah} | ${nameList[numberSurah - 1]}")
        Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp))
        Text(ftsView.transIndo) // TODO: spanstyle?
    }
}
