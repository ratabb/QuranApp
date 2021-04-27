package items

import kotlinx.serialization.Serializable

@Serializable
class Surah(
    val number: Int,
    val sequence: Int = 0,
    val numberOfVerses: Int = 0,
    val name: Name? = null,
    val revelation: Revelation? = null,
    val tafsir: TafsirSurah? = null,
    val verses: List<Ayah> = emptyList() //
)
