package data

import items.dao.AyahDao
import items.dao.SurahDao
import items.entity.AyahEntity
import items.entity.SurahEntity
import items.entity.SurahInfo
import items.entity.SurahWithAyah
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private val surahDao: SurahDao,
    private val ayahDao: AyahDao
) : QuranRepository {

    override suspend fun getSurah(number: Int): SurahEntity =
        surahDao.getSurah(number)

    override suspend fun getAllSurahInfo(): List<SurahInfo> =
        surahDao.getAllSurahInfo()

    override suspend fun getSurahWithAyah(number: Int): SurahWithAyah =
        surahDao.getSurahWithAyah(number)

    override suspend fun insertSurah(surahEntity: List<SurahEntity>) {
        surahEntity.forEach { surahDao.insertSurah(it) }
    }

    override suspend fun updateSurah(surahEntity: SurahEntity) {
        surahDao.updateSurah(surahEntity)
    }

    override suspend fun getAyah(numberInQuran: Int): AyahEntity =
        ayahDao.getAyah(numberInQuran)

    override suspend fun insertAyah(ayahEntity: List<AyahEntity>) {
        ayahEntity.forEach { ayahDao.insertAyah(it) }
    }

    override suspend fun updateAyah(ayahEntity: AyahEntity) {
        ayahDao.updateAyah(ayahEntity)
    }
}
