package id.ratabb.quran.ui.ayah

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import id.ratabb.quran.ui.NavCommand

data class AyahScreenArgs(val numSurah: Int, val indexAyah: Int)

object AyahNavCommand : NavCommand {

    private const val KEY_NUM_SURAH = "numSurah"
    private const val KEY_INDEX_AYAH = "indexAyah"

    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(KEY_NUM_SURAH) { type = NavType.IntType },
        navArgument(KEY_INDEX_AYAH) { type = NavType.IntType }
    )
    override val route: String = "ayah/{$KEY_NUM_SURAH}/{$KEY_INDEX_AYAH}"

    fun destination(numSurah: Int, indexAyah: Int) = "ayah/$numSurah/$indexAyah"

    fun parseArgs(backStackEntry: NavBackStackEntry) = AyahScreenArgs(
        numSurah = backStackEntry.arguments?.getInt(KEY_NUM_SURAH) ?: 0,
        indexAyah = backStackEntry.arguments?.getInt(KEY_INDEX_AYAH) ?: 0
    )
}
