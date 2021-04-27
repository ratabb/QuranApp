package items

import kotlinx.serialization.Serializable

@Serializable
class Audio(
    val primary: String,
    @Suppress("unused") val secondary: Array<String> // TODO: implement this!
)
