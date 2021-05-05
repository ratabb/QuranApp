package id.ratabb.quran.ui.jumpto

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
class JumpToAyahViewModel @Inject constructor(
    private val quranRepository: QuranRepository
) : ViewModel() {

    private val _data = MutableLiveData<List<SurahEntity>>()
    val data: LiveData<List<SurahEntity>> get() = _data

    init {
        viewModelScope.launch(IO) {
            _data.postValue(quranRepository.getAllSurah())
        }
    }
}
