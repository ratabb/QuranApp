package items

import kotlinx.serialization.Serializable

@Serializable
class Text(val arab: String, val transliteration: Transliteration)
