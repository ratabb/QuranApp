package id.ratabb.quran.pref

import kotlinx.coroutines.flow.Flow

interface SearchHistoryPref {
    fun get(): Flow<Set<String>>
    suspend fun add(value: String)
    suspend fun remove(value: String)
    suspend fun clear()
}
