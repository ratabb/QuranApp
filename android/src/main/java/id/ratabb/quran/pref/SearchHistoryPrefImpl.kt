package id.ratabb.quran.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SearchHistoryPrefImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SearchHistoryPref {
    private object Key {
        val searchHistoryKey = stringSetPreferencesKey("searchHistoryKey")
    }

    private val emptySetString = emptySet<String>()

    override fun get(): Flow<Set<String>> = dataStore.data
        .catch { e ->
            if (e is IOException) {
                Timber.e(e)
                emit(emptyPreferences())
            } else throw e
        }
        .map { pref -> pref[Key.searchHistoryKey] ?: emptySetString }

    override suspend fun add(value: String) {
        dataStore.edit { pref ->
            val current = pref[Key.searchHistoryKey] ?: emptySetString
            if (!current.contains(value)) pref[Key.searchHistoryKey] = current + value
        }
    }

    override suspend fun remove(value: String) {
        dataStore.edit { pref ->
            val current = pref[Key.searchHistoryKey] ?: emptySetString
            if (current.contains(value)) pref[Key.searchHistoryKey] = current - value
        }
    }

    override suspend fun clear() {
        dataStore.edit { pref -> pref[Key.searchHistoryKey] = emptySetString }
    }
}
