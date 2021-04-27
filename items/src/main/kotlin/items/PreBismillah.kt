package items

import kotlinx.serialization.Serializable

@Suppress("unused") // TODO: implement this!
@Serializable
class PreBismillah(
    val text: Text,
    val translation: Translation,
    val audio: Audio
)
