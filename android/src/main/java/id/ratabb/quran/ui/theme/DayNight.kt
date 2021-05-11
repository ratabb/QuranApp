package id.ratabb.quran.ui.theme

enum class DayNight {
    LIGHT, DARK, FOLLOW_SYSTEM;

    fun isDark(followSystem: Boolean): Boolean =
        (this === DARK) or ((this === FOLLOW_SYSTEM) and followSystem)
}
