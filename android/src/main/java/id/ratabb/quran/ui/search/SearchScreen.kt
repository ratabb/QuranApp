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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.navigate
import id.ratabb.quran.R
import id.ratabb.quran.ui.AppNavigation
import id.ratabb.quran.ui.LocalNavController
import id.ratabb.quran.ui.common.SearchTextField
import items.entity.AyahFtsView

@Composable
fun SearchScreen() {
    val vm: SearchViewModel = hiltNavGraphViewModel()
    val navController = LocalNavController.current
    val onItemClick: (AyahFtsView) -> Unit = {
        navController.navigate(AppNavigation.ayah(it.numberSurah, it.numberInSurah - 1))
    }
    val state = vm.ayahEntities.observeAsState(emptyList())
    val names = vm.surahName.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }
    Column {
        SearchTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
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
            items(state.value, AyahFtsView::hashCode) {
                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onItemClick(it) },
                    elevation = 8.dp,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                ) {
                    AyahEntityItem(
                        stringResource(
                            R.string.title_item_search_fmt,
                            names.value[it.numberSurah - 1], it.numberSurah, it.numberInSurah
                        ),
                        it.transIndo,
                        Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun AyahEntityItem(title: String, content: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.then(Modifier.padding(8.dp))) {
        Text(title)
        Divider()
        Text(
            buildAnnotatedString {
                // `content` from sql query "snippet(fts, '[',']', '...')"
                // "...begin some text [query] other text ([query]) end..."
                val regex = "(?<=\\[)[^\\[\\]]+(?=])".toRegex()
                append(content.replace("[\\[\\]]".toRegex(), "\u0000")) // `\u0000`=unprintable char
                regex.findAll(content).map(MatchResult::range).forEach {
                    addStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.primary,
                            textDecoration = TextDecoration.Underline
                        ),
                        it.first, it.last + 1
                    )
                }
            }
        )
    }
}
