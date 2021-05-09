package data

import items.entity.AyahEntity
import items.entity.AyahFtsView
import items.entity.SurahEntity
import items.entity.SurahWithAyah

interface QuranRepository {
    suspend fun getSurah(number: Int): SurahEntity
    suspend fun getAllSurah(): List<SurahEntity>
    suspend fun getAllSurahWithAyah(): List<SurahWithAyah>
    suspend fun getSurahWithAyah(number: Int): SurahWithAyah
    suspend fun insertSurah(surahEntity: List<SurahEntity>)
    suspend fun updateSurah(surahEntity: SurahEntity)

    suspend fun getAyah(numberInQuran: Int): AyahEntity
    suspend fun insertAyah(ayahEntity: List<AyahEntity>)
    suspend fun updateAyah(ayahEntity: AyahEntity)
    suspend fun searchAyah(query: String): List<AyahFtsView>
}
