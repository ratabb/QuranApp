package items

import kotlinx.serialization.Serializable

@Suppress("unused") // TODO: implement this! atm: juz & sajda only.
@Serializable
class Meta(
    val juz: Int,
    val page: Int,
    val manzil: Int,
    val ruku: Int,
    val hizbQuarter: Int,
    val sajda: Sajda
)
