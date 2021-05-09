package items.entity

import androidx.compose.runtime.Immutable

@Immutable
data class AyahFtsView(
    val numberSurah: Int,
    val numberInSurah: Int,
    val transIndo: String
)
