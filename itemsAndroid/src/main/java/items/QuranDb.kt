package items

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import items.dao.AyahDao
import items.dao.SurahDao
import items.entity.AyahEntity
import items.entity.AyahFts
import items.entity.SurahEntity

@Database(
    entities = [AyahEntity::class, AyahFts::class, SurahEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuranDb : RoomDatabase() {
    abstract fun getAyahDao(): AyahDao
    abstract fun getSurahDao(): SurahDao

    companion object {
        inline fun build(
            context: Context,
            builder: Builder<QuranDb>.() -> Builder<QuranDb>
        ): QuranDb = Room.databaseBuilder(context, QuranDb::class.java, "quran")
            .also { builder(it) }.build()
    }
}
