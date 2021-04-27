package items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TafsirId(val short: String? = null, val long: String? = null)

@Serializable
class TafsirAyah(@SerialName("id") val tafsirId: TafsirId)

@Serializable
class TafsirSurah(@SerialName("id") val id: String? = null)
