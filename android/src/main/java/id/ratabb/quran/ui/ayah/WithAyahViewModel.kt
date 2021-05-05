package id.ratabb.quran.ui.ayah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.QuranRepository
import items.entity.AyahEntity
import items.entity.SurahWithAyah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WithAyahViewModel @Inject constructor(
    private val quranRepository: QuranRepository
) : ViewModel() {

    private val _withAyahData = MutableLiveData<SurahWithAyah>()
    val withAyahData: LiveData<SurahWithAyah> get() = _withAyahData

    private val _indexAyahData = MutableLiveData(1)
    val indexAyahData: LiveData<Int> get() = _indexAyahData

    private val _basmalah = MutableLiveData<AyahEntity>()
    val basmalah: LiveData<AyahEntity> get() = _basmalah

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _basmalah.postValue(quranRepository.getAyah(1))
            }
        }
    }

    fun setSurahAyah(numSurah: Int, indexAyah: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _withAyahData.postValue(
                    quranRepository.getSurahWithAyah(numSurah)
                )
                _indexAyahData.postValue(indexAyah)
            }
        }
    }
}
