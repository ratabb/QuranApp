package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "ayah")
data class AyahEntity(
    @PrimaryKey(autoGenerate = false)
    val numberInQuran: Int,
    val numberInSurah: Int,
    val numberSurah: Int,
    val juz: Int,
    val sajda: Boolean,
    val textArabic: String,
    val textEnglish: String? = null,
    val textIndo: String? = null,
    val transIndo: String? = null,
    val transEnglish: String? = null,
    val audioUrl: String? = null,
    val tafsirShort: String? = null,
    val tafsirLong: String? = null
)
