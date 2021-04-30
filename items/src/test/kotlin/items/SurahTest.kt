package items

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.equalToIgnoringCase
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStream

class SurahTest {

    private val jsonFormat: Json by lazy {
        Json { ignoreUnknownKeys = true; encodeDefaults = true }
    }

    @Test
    fun `Load ayah_2_255 json from Resources`() {
        val data = "ayah_2_255.json".loadStreamAsText()

        val dataSingleAyah: DataSingleAyah = jsonFormat.decodeFromString(data)

        assertThat(dataSingleAyah, `is`(notNullValue(DataSingleAyah::class.java)))

        assertThat(dataSingleAyah.data.numberSurah?.number, `is`(equalTo(2)))

        assertThat(
            dataSingleAyah.data.audio?.primary,
            `is`(equalToIgnoringCase("https://cdn.alquran.cloud/media/audio/ayah/ar.alafasy/262"))
        )

        assertThat(dataSingleAyah.data.number?.inSurah, `is`(equalTo(255)))

        //
        assertThat(
            dataSingleAyah.data.text?.arab,
            `is`(
                equalToIgnoringCase(
                    "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ ۚ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ ۚ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ ۗ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلَّا بِإِذْنِهِ ۚ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ ۖ وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَاءَ ۚ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ ۖ وَلَا يَئُودُهُ حِفْظُهُمَا ۚ وَهُوَ الْعَلِيُّ الْعَظِيمُ"
                )
            )
        )
    }

    @Test
    fun `Load surah_110 json from Resources`() {
        val data = "surah_110.json".loadStreamAsText()

        val dataSingleSurah: DataSingleSurah = jsonFormat.decodeFromString(data)

        assertThat(dataSingleSurah, `is`(notNullValue(DataSingleSurah::class.java)))

        assertThat(dataSingleSurah.data.name?.short, `is`(equalToIgnoringCase("النصر")))
    }

    @Test
    fun `Load all_surah json from Resources`() {
        val data = "all_surah.json".loadStreamAsText()

        val dataListSurah: DataListSurah = jsonFormat.decodeFromString(data)

        assertThat(dataListSurah, `is`(notNullValue(DataListSurah::class.java)))

        assertThat(dataListSurah.data[0].name?.short, `is`(equalToIgnoringCase("الفاتحة")))
    }

    private fun String.loadStreamAsText(): String =
        loadStream().bufferedReader().use(BufferedReader::readText)

    private fun String.loadStream(): InputStream =
        SurahTest::class.java.classLoader.getResourceAsStream(this)
            ?: throw IllegalStateException("Can't load $this")
}
