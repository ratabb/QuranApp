package items.entity

import androidx.compose.runtime.Immutable

@Immutable
data class AyahFtsView(
    val numberSurah: Int,
    val numberInSurah: Int,
    val transIndo: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AyahFtsView
        if (numberSurah != other.numberSurah) return false
        if (numberInSurah != other.numberInSurah) return false
        if (transIndo != other.transIndo) return false
        return true
    }

    override fun hashCode(): Int {
        var result = numberSurah
        result = 31 * result + numberInSurah
        result = 31 * result + transIndo.hashCode()
        return result
    }

    override fun toString() = """AyahFtsView(
        |numberSurah=$numberSurah,
        |numberInSurah=$numberInSurah,
        |transIndo='$transIndo')""".trimMargin()
}
