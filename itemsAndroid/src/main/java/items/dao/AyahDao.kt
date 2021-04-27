package items.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import items.entity.AyahEntity

@Dao
abstract class AyahDao {

    @Query("SELECT * FROM ayah WHERE numberInQuran == :numberInQuran LIMIT 1")
    abstract suspend fun getAyah(numberInQuran: Int): AyahEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAyah(ayahEntity: AyahEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun updateAyah(ayahEntity: AyahEntity)
}
