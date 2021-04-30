package items.entity

import androidx.compose.runtime.Immutable

@Immutable
data class SurahInfo(
    val number: Int,
    val textArabic: String,
    val textEnglish: String,
    val textIndo: String
)
