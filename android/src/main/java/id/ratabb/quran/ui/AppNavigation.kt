package id.ratabb.quran.ui

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class AppNavigation(val route: String)

object NavSurah : AppNavigation("surah")

private const val defaultIndexAyah = 0
private const val argNumSurah = "numSurah"
private const val argIndexAyah = "indexAyah"

object NavAyah : AppNavigation("ayah/{$argNumSurah}/{$argIndexAyah}") {

    val arguments: List<NamedNavArgument> = listOf(
        navArgument(argNumSurah) { type = NavType.IntType },
        navArgument(argIndexAyah) {
            type = NavType.IntType
            defaultValue = defaultIndexAyah
        }
    )

    fun getNumSurah(entry: NavBackStackEntry): Int =
        entry.arguments?.getInt(argNumSurah) ?: throw IllegalStateException("Can't get numSurah")

    fun getIndexAyah(entry: NavBackStackEntry): Int =
        entry.arguments?.getInt(argIndexAyah) ?: defaultIndexAyah

    @JvmOverloads
    fun ayahWith(numSurah: Int, indexAyah: Int = defaultIndexAyah): String =
        "ayah/$numSurah/$indexAyah"
}

object NavSearch : AppNavigation("search")
