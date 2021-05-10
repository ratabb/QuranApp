package id.ratabb.quran.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.createGraph
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import id.ratabb.quran.ui.ayah.AyahNavCommand
import id.ratabb.quran.ui.ayah.AyahScreen
import id.ratabb.quran.ui.common.FunctionalityNotAvailablePopup
import id.ratabb.quran.ui.common.QuranAppBar
import id.ratabb.quran.ui.jumpto.JumpToAyahPopup
import id.ratabb.quran.ui.search.SearchNavCommand
import id.ratabb.quran.ui.search.SearchScreen
import id.ratabb.quran.ui.surah.SurahNavCommand
import id.ratabb.quran.ui.surah.SurahScreen

@Composable
fun QuranAppUI() {
    val navController = rememberNavController()
    val navGraph: NavGraph = navController.createGraph(startDestination = AppNavigation.root()) {
        navigationNode(SurahNavCommand) { SurahScreen() }
        navigationNode(AyahNavCommand) { AyahScreen(AyahNavCommand.parseArgs(it)) }
        navigationNode(SearchNavCommand) { SearchScreen() }
    }
    var isShowUnimplement by remember { mutableStateOf(false) }
    var isJumpToAyah by remember { mutableStateOf(false) }

    ProvideWindowInsets {
        CompositionLocalProvider(LocalNavController provides navController) {
            Scaffold(
                topBar = {
                    QuranAppBar(
                        backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.87f),
                        modifier = Modifier.statusBarsPadding().fillMaxWidth(),
                        onSearchClick = { navController.navigate(AppNavigation.search()) },
                        onJumpToClick = { isJumpToAyah = true },
                        onMoreClick = { isShowUnimplement = true }
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                content = { NavHost(navController, navGraph) }
            )
            FunctionalityNotAvailablePopup(isShowUnimplement) { isShowUnimplement = false }
            JumpToAyahPopup(isJumpToAyah) { isJumpToAyah = false }
        }
    }
}
