package items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Ayah(
    val number: Number? = null,
    val meta: Meta? = null,
    val text: Text? = null,
    val translation: Translation? = null,
    val audio: Audio? = null,
    val tafsir: TafsirAyah? = null,
    @SerialName("surah")
    val numberSurah: NumberSurah? = null,
)
