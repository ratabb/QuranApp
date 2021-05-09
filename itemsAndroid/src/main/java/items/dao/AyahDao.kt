package items.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import items.entity.AyahEntity
import items.entity.AyahFtsView

@Dao
abstract class AyahDao {

    @Query("SELECT * FROM ayah WHERE numberInQuran == :numberInQuran LIMIT 1")
    abstract suspend fun getAyah(numberInQuran: Int): AyahEntity

    @Insert
    abstract suspend fun insertAyah(ayahEntity: AyahEntity): Long

    @Update
    abstract suspend fun updateAyah(ayahEntity: AyahEntity)

    @Transaction
    @Query(
        "SELECT ayah.numberSurah,ayah.numberInSurah," +
            "snippet(ayahFts, '[', ']', '...') AS transIndo " +
            "FROM ayah JOIN ayahFts ON ayah.rowid == ayahFts.rowid " +
            "WHERE ayahFts MATCH :query"
    )
    abstract fun searchAyah(query: String): List<AyahFtsView>
}
