package items

import kotlinx.serialization.Serializable

@Serializable
class Name(
    val short: String,
    val long: String,
    val transliteration: Transliteration,
    val translation: Translation,
)
