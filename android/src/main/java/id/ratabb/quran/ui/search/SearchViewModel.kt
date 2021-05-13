package id.ratabb.quran.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.QuranRepository
import id.ratabb.quran.pref.SearchHistoryPref
import items.entity.AyahFtsView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val quranRepository: QuranRepository,
    private val searchHistoryPref: SearchHistoryPref
) : ViewModel() {

    val searchHistory = searchHistoryPref.get().asLiveData()

    private val _ayahEntities = MutableLiveData<List<AyahFtsView>>()
    val ayahEntities: LiveData<List<AyahFtsView>>
        get() = _ayahEntities

    private val _surahName = MutableLiveData<List<String>>()
    val surahName: LiveData<List<String>> get() = _surahName

    init {
        viewModelScope.launch(IO) {
            _surahName.postValue(quranRepository.getAllSurah().map { it.textIndo!! })
        }
    }

    fun search(query: String) {
        val trimedQuery = query.trim()
        if (trimedQuery.isEmpty()) {
            _ayahEntities.value = emptyList()
        } else {
            viewModelScope.launch(IO) {
                addSearchHistory(trimedQuery)
                _ayahEntities.postValue(quranRepository.searchAyah(trimedQuery.sanitize()))
            }
        }
    }

    fun removeSearchHistory(value: String) {
        viewModelScope.launch(IO) { searchHistoryPref.remove(value) }
    }

    fun clearSearchHistory() {
        viewModelScope.launch(IO) { searchHistoryPref.clear() }
    }

    private fun String.sanitize(): String {
        if (isEmpty()) return ""
        val queryWithEscapedQuotes = this.replace(Regex.fromLiteral("\""), "\"\"")
        return "*\"$queryWithEscapedQuotes\"*"
    }

    private suspend fun addSearchHistory(value: String) {
        searchHistoryPref.add(value)
    }
}
