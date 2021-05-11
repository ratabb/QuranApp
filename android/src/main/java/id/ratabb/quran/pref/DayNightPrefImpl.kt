package id.ratabb.quran.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import id.ratabb.quran.ui.theme.DayNight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class DayNightPrefImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DayNightPref {

    private object Key {
        val dayNightKey = stringPreferencesKey("dayNight")
    }

    override fun get(): Flow<DayNight> = dataStore.data
        .catch { e ->
            if (e is IOException) {
                Timber.e(e)
                emit(emptyPreferences())
            } else throw e
        }.map { pref ->
            DayNight.valueOf(pref[Key.dayNightKey] ?: DayNight.FOLLOW_SYSTEM.name)
        }

    override suspend fun set(dayNight: DayNight) {
        dataStore.edit { pref -> pref[Key.dayNightKey] = dayNight.name }
    }
}
