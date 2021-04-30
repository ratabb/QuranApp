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
)
