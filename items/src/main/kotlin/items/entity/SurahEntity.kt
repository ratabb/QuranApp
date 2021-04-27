package items.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey(autoGenerate = false)
    val number: Int = 0,
    val sequence: Int = 0,
    val numberOfVerses: Int = 0,
    val textArabic: String? = null,
    val textArabicLong: String? = null,
    val textEnglish: String? = null,
    val textIndo: String? = null,
    val transEnglish: String? = null,
    val transIndo: String? = null,
    val revArabic: String? = null,
    val revEnglish: String? = null,
    val revIndo: String? = null,
    val tafsir: String? = null
)
