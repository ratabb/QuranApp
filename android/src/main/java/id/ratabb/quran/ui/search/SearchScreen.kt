package id.ratabb.quran.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
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
    val state = vm.ayahEntities.observeAsState(emptyList())
    val names = vm.surahName.observeAsState(emptyList())
    val history = vm.searchHistory.observeAsState(emptySet())
    SearchContent(
        list = state.value,
        nameLookup = names.value,
        history = history.value,
        onItemClick = navController::navigate,
        onSearch = vm::search,
        onRemoveHistory = vm::removeSearchHistory,
        onClearHistory = vm::clearSearchHistory
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchContent(
    list: List<AyahFtsView>,
    nameLookup: List<String>,
    history: Set<String>,
    onItemClick: (String) -> Unit,
    onSearch: (String) -> Unit,
    onRemoveHistory: (String) -> Unit,
    onClearHistory: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var isShowSuggestion by remember { mutableStateOf(false) }

    Column {
        // region Search
        SearchTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            onSearch = onSearch,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .onFocusChanged { isShowSuggestion = it == FocusState.Active },
            label = { Text(stringResource(R.string.search)) },
            placeholder = { Text(stringResource(R.string.search_ayah_by_trans)) },
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(
                        onClick = {
                            searchQuery = ""
                            onSearch(searchQuery)
                        }
                    ) {
                        Icon(Icons.Default.Clear, null)
                    }
                }
            },
            leadingIcon = {
                AnimatedVisibility(visible = list.isNotEmpty()) {
                    Surface(
                        modifier = Modifier.size(36.dp),
                        shape = MaterialTheme.shapes.small,
                        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${list.size}")
                        }
                    }
                }
            }
        )
        // endregion
        // region Suggestion
        SuggestionContent(
            isShowSuggestion = isShowSuggestion,
            suggestions = { history.filter { it.startsWith(searchQuery, true) } },
            onClickSuggestion = { suggestion ->
                isShowSuggestion = false
                searchQuery = suggestion
                onSearch(searchQuery)
            },
            onRemoveSuggestion = onRemoveHistory,
            onClearSuggestion = onClearHistory
        )
        // endregion
        // region Result
        SearchResultList(
            list = list,
            nameLookup = nameLookup,
            onItemClick = onItemClick
        )
        // endregion
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuggestionContent(
    isShowSuggestion: Boolean,
    suggestions: () -> List<String>,
    onClickSuggestion: (String) -> Unit,
    onRemoveSuggestion: (String) -> Unit,
    onClearSuggestion: () -> Unit
) {
    AnimatedVisibility(
        visible = isShowSuggestion and suggestions().isNotEmpty(),
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Card(
            modifier = Modifier.padding(all = 8.dp),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, MaterialTheme.colors.primary)
        ) {
            Column(
                modifier = Modifier
                    .heightIn(max = 180.dp)
                    .padding(all = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .clickable { onClearSuggestion() }
                ) {
                    Icon(Icons.Default.History, null)
                    Text(
                        text = stringResource(R.string.clear_all),
                        modifier = Modifier.fillMaxWidth().weight(1F).padding(horizontal = 8.dp)
                    )
                    Icon(Icons.Default.ClearAll, null)
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                LazyColumn {
                    items(suggestions()) { suggestion ->
                        Row {
                            Text(
                                text = suggestion,
                                modifier = Modifier.fillMaxWidth().weight(1F)
                                    .padding(horizontal = 8.dp)
                                    .clickable { onClickSuggestion(suggestion) }
                            )
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                modifier = Modifier.clickable { onRemoveSuggestion(suggestion) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultList(
    list: List<AyahFtsView>,
    nameLookup: List<String>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list, AyahFtsView::hashCode) { ftsView ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        onItemClick(
                            AppNavigation.ayah(ftsView.numberSurah, ftsView.numberInSurah - 1)
                        )
                    },
                elevation = 8.dp,
                border = BorderStroke(1.dp, MaterialTheme.colors.primary)
            ) {
                AyahEntityItem(
                    nameLookup = nameLookup,
                    ayahFtsView = ftsView,
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AyahEntityItem(
    nameLookup: List<String>,
    ayahFtsView: AyahFtsView,
    modifier: Modifier = Modifier,
) {
    val content = ayahFtsView.transIndo
    val contentQueryStyle = SpanStyle(
        color = MaterialTheme.colors.primary,
        textDecoration = TextDecoration.Underline
    )
    val queryMatcher = "(?<=\\[)[^\\[\\]]+(?=])".toRegex()
    val marker = "[\\[\\]]".toRegex()
    val nullChar = "\u0000" // unprintable char
    Column(modifier = modifier.then(Modifier.padding(8.dp))) {
        Text(
            text = stringResource(
                R.string.title_item_search_fmt,
                nameLookup[ayahFtsView.numberSurah - 1],
                ayahFtsView.numberSurah,
                ayahFtsView.numberInSurah
            )
        )
        Divider()
        Text(
            text = buildAnnotatedString {
                append(content.replace(marker, nullChar))
                queryMatcher.findAll(content).map(MatchResult::range)
                    .forEach { addStyle(contentQueryStyle, it.first, it.last + 1) }
            }
        )
    }
}
