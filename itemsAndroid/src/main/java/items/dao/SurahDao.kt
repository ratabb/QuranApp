package items.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import items.entity.SurahEntity
import items.entity.SurahInfo
import items.entity.SurahWithAyah

@Dao
abstract class SurahDao {

    @Query("SELECT * FROM surah WHERE number == :number LIMIT 1")
    abstract suspend fun getSurah(number: Int): SurahEntity

    @Query("SELECT number,textArabic,textEnglish,textIndo FROM surah")
    abstract fun getAllSurahInfo(): List<SurahInfo>

    @Transaction
    @Query("SELECT * FROM surah WHERE number == :number LIMIT 1")
    abstract suspend fun getSurahWithAyah(number: Int): SurahWithAyah

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertSurah(surahEntity: SurahEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun updateSurah(surahEntity: SurahEntity)
}
