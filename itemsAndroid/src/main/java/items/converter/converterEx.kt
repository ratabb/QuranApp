package items.converter

import items.Ayah
import items.Surah
import items.entity.AyahEntity
import items.entity.SurahEntity

fun Surah.toEntity() = SurahEntity(
    number = number,
    sequence = sequence,
    numberOfVerses = numberOfVerses,
    textArabic = name!!.short,
    textArabicLong = name!!.long,
    textEnglish = name!!.transliteration.en,
    textIndo = name!!.transliteration.id,
    transEnglish = name!!.translation.en,
    transIndo = name!!.translation.id,
    revArabic = revelation?.arab,
    revEnglish = revelation?.en,
    revIndo = revelation?.id,
    tafsir = tafsir?.id

)

fun Ayah.toEntity(numberSurah: Int): AyahEntity = AyahEntity(
    numberInQuran = number!!.inQuran,
    numberInSurah = number!!.inSurah,
    numberSurah = numberSurah,
    juz = meta!!.juz,
    sajda = meta!!.sajda.run { recommended || obligatory },
    textArabic = text!!.arab,
    textEnglish = text!!.transliteration.en,
    textIndo = text!!.transliteration.id,
    transEnglish = translation?.en,
    transIndo = translation?.id,
    audioUrl = audio?.primary,
    tafsirShort = tafsir?.tafsirId?.short,
    tafsirLong = tafsir?.tafsirId?.long
)
