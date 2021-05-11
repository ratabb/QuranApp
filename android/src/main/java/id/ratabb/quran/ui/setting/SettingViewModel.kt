package id.ratabb.quran.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ratabb.quran.pref.DayNightPref
import id.ratabb.quran.ui.theme.DayNight
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dayNightPref: DayNightPref
) : ViewModel() {
    val dayNight: LiveData<DayNight> =
        dayNightPref.get().asLiveData(viewModelScope.coroutineContext)

    fun setDayNight(dayNight: DayNight) {
        viewModelScope.launch { dayNightPref.set(dayNight) }
    }
}
