package items.converter

import items.DataListSurah
import items.Surah
import items.entity.AyahEntity
import items.entity.SurahEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStream

class QuranJsonToEntity(inputStream: InputStream, json: Json) {
    private val dataListSurah: DataListSurah = inputStream.bufferedReader()
        .use(BufferedReader::readText).let(json::decodeFromString)

    fun getAyahEntity(): List<AyahEntity> = dataListSurah.data.mapIndexed { indexSurah, surah ->
        surah.verses.map { ayah -> ayah.toEntity(indexSurah + 1) }
    }.flatten()

    fun getSurahEntity(): List<SurahEntity> = dataListSurah.data.map(Surah::toEntity)
}
