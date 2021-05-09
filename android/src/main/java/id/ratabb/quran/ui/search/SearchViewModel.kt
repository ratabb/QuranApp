package id.ratabb.quran.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.QuranRepository
import items.entity.AyahFtsView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val quranRepository: QuranRepository
) : ViewModel() {

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
        if (query.isEmpty()) _ayahEntities.value = emptyList()
        viewModelScope.launch(IO) {
            _ayahEntities.postValue(quranRepository.searchAyah(query.sanitize()))
        }
    }

    private fun String.sanitize(): String {
        if (isEmpty()) return ""
        val queryWithEscapedQuotes = this.replace(Regex.fromLiteral("\""), "\"\"")
        return "*\"$queryWithEscapedQuotes\"*"
    }
}
