package items.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import items.entity.SurahEntity
import items.entity.SurahWithAyah

@Dao
abstract class SurahDao {

    @Query("SELECT * FROM surah WHERE number == :number LIMIT 1")
    abstract suspend fun getSurah(number: Int): SurahEntity

    @Query("SELECT * FROM surah")
    abstract fun getAllSurah(): List<SurahEntity>

    @Transaction
    @Query("SELECT * FROM surah WHERE number == :number LIMIT 1")
    abstract suspend fun getSurahWithAyah(number: Int): SurahWithAyah

    @Transaction
    @Query("SELECT * FROM surah")
    abstract fun getAllSurahWithAyah(): List<SurahWithAyah>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertSurah(surahEntity: SurahEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun updateSurah(surahEntity: SurahEntity)
}
