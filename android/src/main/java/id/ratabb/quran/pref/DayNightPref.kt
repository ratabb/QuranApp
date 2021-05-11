package id.ratabb.quran.pref

import id.ratabb.quran.ui.theme.DayNight
import kotlinx.coroutines.flow.Flow

interface DayNightPref {
    fun get(): Flow<DayNight>
    suspend fun set(dayNight: DayNight)
}
