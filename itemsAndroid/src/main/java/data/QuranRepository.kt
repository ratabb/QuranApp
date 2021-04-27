package data

import items.entity.AyahEntity
import items.entity.SurahEntity
import items.entity.SurahInfo
import items.entity.SurahWithAyah

interface QuranRepository {
    suspend fun getSurah(number: Int): SurahEntity
    suspend fun getAllSurahInfo(): List<SurahInfo>
    suspend fun getSurahWithAyah(number: Int): SurahWithAyah
    suspend fun insertSurah(surahEntity: List<SurahEntity>)
    suspend fun updateSurah(surahEntity: SurahEntity)

    suspend fun getAyah(numberInQuran: Int): AyahEntity
    suspend fun insertAyah(ayahEntity: List<AyahEntity>)
    suspend fun updateAyah(ayahEntity: AyahEntity)
}
