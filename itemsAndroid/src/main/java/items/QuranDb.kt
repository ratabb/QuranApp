package items

import androidx.room.Database
import androidx.room.RoomDatabase
import items.dao.AyahDao
import items.dao.SurahDao
import items.entity.AyahEntity
import items.entity.SurahEntity

@Database(
    entities = [AyahEntity::class, SurahEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuranDb : RoomDatabase() {
    abstract fun getAyahDao(): AyahDao
    abstract fun getSurahDao(): SurahDao
}
