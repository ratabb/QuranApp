package id.ratabb.quran.ui.surah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.QuranRepository
import items.entity.SurahInfo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SurahInfoViewModel @Inject constructor(
    private val quranRepository: QuranRepository
) : ViewModel() {
    private val _surahData = MutableLiveData<List<SurahInfo>>()
    val surahData: LiveData<List<SurahInfo>>
        get() = _surahData

    init {
        viewModelScope.launch {
            withContext(IO) { _surahData.postValue(quranRepository.getAllSurahInfo()) }
        }
    }
}
