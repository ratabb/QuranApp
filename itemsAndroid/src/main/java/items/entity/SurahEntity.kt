package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "surah")
data class SurahEntity(
    @PrimaryKey
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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SurahEntity
        if (number != other.number) return false
        if (sequence != other.sequence) return false
        if (numberOfVerses != other.numberOfVerses) return false
        if (textArabic != other.textArabic) return false
        if (textArabicLong != other.textArabicLong) return false
        if (textEnglish != other.textEnglish) return false
        if (textIndo != other.textIndo) return false
        if (transEnglish != other.transEnglish) return false
        if (transIndo != other.transIndo) return false
        if (revArabic != other.revArabic) return false
        if (revEnglish != other.revEnglish) return false
        if (revIndo != other.revIndo) return false
        if (tafsir != other.tafsir) return false
        return true
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + sequence
        result = 31 * result + numberOfVerses
        result = 31 * result + (textArabic?.hashCode() ?: 0)
        result = 31 * result + (textArabicLong?.hashCode() ?: 0)
        result = 31 * result + (textEnglish?.hashCode() ?: 0)
        result = 31 * result + (textIndo?.hashCode() ?: 0)
        result = 31 * result + (transEnglish?.hashCode() ?: 0)
        result = 31 * result + (transIndo?.hashCode() ?: 0)
        result = 31 * result + (revArabic?.hashCode() ?: 0)
        result = 31 * result + (revEnglish?.hashCode() ?: 0)
        result = 31 * result + (revIndo?.hashCode() ?: 0)
        result = 31 * result + (tafsir?.hashCode() ?: 0)
        return result
    }

    override fun toString() = """SurahEntity(
        |number=$number,
        |sequence=$sequence,
        |numberOfVerses=$numberOfVerses,
        |textArabic=$textArabic,
        |textArabicLong=$textArabicLong,
        |textEnglish=$textEnglish,
        |textIndo=$textIndo,
        |transEnglish=$transEnglish,
        |transIndo=$transIndo,
        |revArabic=$revArabic,
        |revEnglish=$revEnglish,
        |revIndo=$revIndo,
        |tafsir=$tafsir)""".trimMargin()
}
