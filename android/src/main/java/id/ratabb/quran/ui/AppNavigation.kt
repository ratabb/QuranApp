package id.ratabb.quran.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.composable
import id.ratabb.quran.ui.ayah.AyahNavCommand
import id.ratabb.quran.ui.search.SearchNavCommand
import id.ratabb.quran.ui.setting.SettingNavCommand
import id.ratabb.quran.ui.surah.SurahNavCommand

val LocalNavController = staticCompositionLocalOf<NavController> {
    error("No local navigator found!")
}

interface NavCommand {
    val arguments: List<NamedNavArgument> get() = emptyList()
    val route: String
}

fun NavGraphBuilder.navigationNode(
    command: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = command.route,
        arguments = command.arguments,
        content = content
    )
}

object AppNavigation {
    fun root() = surah()
    fun surah() = SurahNavCommand.route
    fun ayah(numSurah: Int, indexAyah: Int) = AyahNavCommand.destination(numSurah, indexAyah)
    fun search() = SearchNavCommand.route
    fun setting() = SettingNavCommand.route
}
