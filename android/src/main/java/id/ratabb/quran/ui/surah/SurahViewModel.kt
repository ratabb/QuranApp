package id.ratabb.quran.ui.surah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.QuranRepository
import items.entity.SurahEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(
    private val quranRepository: QuranRepository
) : ViewModel() {
    private val _surahData = MutableLiveData<List<SurahEntity>>()
    val surahData: LiveData<List<SurahEntity>>
        get() = _surahData

    init {
        viewModelScope.launch(IO) {
            _surahData.postValue(quranRepository.getAllSurah())
        }
    }
}
