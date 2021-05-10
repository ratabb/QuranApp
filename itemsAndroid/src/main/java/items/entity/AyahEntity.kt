package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "ayah")
data class AyahEntity(
    @PrimaryKey
    val numberInQuran: Int,
    val numberInSurah: Int,
    val numberSurah: Int,
    val juz: Int,
    val sajda: Boolean,
    val textArabic: String,
    val textEnglish: String? = null,
    val transIndo: String? = null,
    val transEnglish: String? = null,
    val audioUrl: String? = null,
    val tafsirShort: String? = null,
    val tafsirLong: String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AyahEntity
        if (numberInQuran != other.numberInQuran) return false
        if (numberInSurah != other.numberInSurah) return false
        if (numberSurah != other.numberSurah) return false
        if (juz != other.juz) return false
        if (sajda != other.sajda) return false
        if (textArabic != other.textArabic) return false
        if (textEnglish != other.textEnglish) return false
        if (transIndo != other.transIndo) return false
        if (transEnglish != other.transEnglish) return false
        if (audioUrl != other.audioUrl) return false
        if (tafsirShort != other.tafsirShort) return false
        if (tafsirLong != other.tafsirLong) return false
        return true
    }

    override fun hashCode(): Int {
        var result = numberInQuran
        result = 31 * result + numberInSurah
        result = 31 * result + numberSurah
        result = 31 * result + juz
        result = 31 * result + sajda.hashCode()
        result = 31 * result + textArabic.hashCode()
        result = 31 * result + (textEnglish?.hashCode() ?: 0)
        result = 31 * result + (transIndo?.hashCode() ?: 0)
        result = 31 * result + (transEnglish?.hashCode() ?: 0)
        result = 31 * result + (audioUrl?.hashCode() ?: 0)
        result = 31 * result + (tafsirShort?.hashCode() ?: 0)
        result = 31 * result + (tafsirLong?.hashCode() ?: 0)
        return result
    }

    override fun toString() = """AyahEntity(
        |numberInQuran=$numberInQuran,
        |numberInSurah=$numberInSurah,
        |numberSurah=$numberSurah,
        |juz=$juz,
        |sajda=$sajda,
        |textArabic='$textArabic',
        |textEnglish=$textEnglish,
        |transIndo=$transIndo,
        |transEnglish=$transEnglish,
        |audioUrl=$audioUrl,
        |tafsirShort=$tafsirShort,
        |tafsirLong=$tafsirLong)""".trimMargin()
}
