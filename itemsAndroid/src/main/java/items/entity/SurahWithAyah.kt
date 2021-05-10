package items.entity

import androidx.compose.runtime.Immutable
import androidx.room.Embedded
import androidx.room.Relation

@Immutable
data class SurahWithAyah(
    @Embedded val surahEntity: SurahEntity,
    @Relation(
        parentColumn = "number",
        entityColumn = "numberSurah"
    ) val ayahEntity: List<AyahEntity>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as SurahWithAyah
        if (surahEntity != other.surahEntity) return false
        if (ayahEntity != other.ayahEntity) return false
        return true
    }

    override fun hashCode(): Int {
        var result = surahEntity.hashCode()
        result = 31 * result + ayahEntity.hashCode()
        return result
    }

    override fun toString() = "SurahWithAyah(surahEntity=$surahEntity, ayahEntity=$ayahEntity)"
}
