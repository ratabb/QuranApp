package items.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SurahWithAyah(
    @Embedded val surahEntity: SurahEntity,
    @Relation(
        parentColumn = "number",
        entityColumn = "numberSurah"
    ) val ayahEntity: List<AyahEntity>
)
