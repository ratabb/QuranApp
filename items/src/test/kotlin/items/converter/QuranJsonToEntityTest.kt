package items.converter

import items.entity.AyahEntity
import items.entity.SurahEntity
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.equalToIgnoringCase
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import java.io.InputStream

class QuranJsonToEntityTest {

    private companion object {
        const val TOTAL_AYAH = 6236
        const val TOTAL_SURAH = 114
        const val QURAN_JSON = "quran.json"

        // NOTE: IN QURAN
        const val FIRST_SURAH_NAME_ID = "Al-Fatihah"
        const val LAST_SURAH_NAME_ID = "An-Nas"
        const val FIRST_AYAH_EN = "Bismillaahir Rahmaanir Raheem"
        const val LAST_AYAH_EN = "Minal jinnati wan naas"

        val jsonForamat = Json { ignoreUnknownKeys = true; encodeDefaults = true }
    }

    private val quranJsonToEntity: QuranJsonToEntity =
        QuranJsonToEntity(QURAN_JSON.loadStream(), jsonForamat)

    @Test
    fun getAyahEntity() {
        val actual: List<AyahEntity> = quranJsonToEntity.getAyahEntity()

        assertThat(actual, `is`(notNullValue()))

        assertThat(actual.size, `is`(equalTo(TOTAL_AYAH)))

        assertThat(actual.first().numberSurah, `is`(equalTo(1)))
        assertThat(actual.first().textEnglish, `is`(equalToIgnoringCase(FIRST_AYAH_EN)))

        assertThat(actual.last().numberSurah, `is`(equalTo(TOTAL_SURAH)))
        assertThat(actual.last().textEnglish, `is`(equalToIgnoringCase(LAST_AYAH_EN)))
    }

    @Test
    fun getSurahEntity() {
        val actual: List<SurahEntity> = quranJsonToEntity.getSurahEntity()

        assertThat(actual, `is`(notNullValue()))

        assertThat(actual.size, `is`(equalTo(TOTAL_SURAH)))

        assertThat(actual.first().textIndo, `is`(equalToIgnoringCase(FIRST_SURAH_NAME_ID)))

        assertThat(actual.last().textIndo, `is`(equalToIgnoringCase(LAST_SURAH_NAME_ID)))
    }

    private fun String.loadStream(): InputStream =
        QuranJsonToEntityTest::class.java.classLoader.getResourceAsStream(this)
            ?: throw IllegalStateException("Can't load $this")
}
