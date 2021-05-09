package id.ratabb.quran.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LowPriority
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.createGraph
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.ratabb.quran.R
import id.ratabb.quran.ui.ayah.WithAyahScreen
import id.ratabb.quran.ui.ayah.WithAyahViewModel
import id.ratabb.quran.ui.common.FunctionalityNotAvailablePopup
import id.ratabb.quran.ui.jumpto.JumpToAyah
import id.ratabb.quran.ui.search.SearchScreen
import id.ratabb.quran.ui.search.SearchViewModel
import id.ratabb.quran.ui.surah.SurahInfoListScreen
import id.ratabb.quran.ui.surah.SurahInfoViewModel

@Composable
fun QuranAppUI() {
    val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)
    val navController = rememberNavController()

    val navGraph: NavGraph = navController.createGraph(startDestination = NavSurah.route) {
        composable(route = NavSurah.route) { backStackEntry ->
            val viewModel: SurahInfoViewModel = hiltNavGraphViewModel(backStackEntry)
            SurahInfoListScreen(viewModel) { navController.navigate(NavAyah.ayahWith(it)) }
        }
        composable(
            route = NavAyah.route,
            arguments = NavAyah.arguments
        ) { backStackEntry ->
            val numSurah = NavAyah.getNumSurah(backStackEntry)
            val indexAyah = NavAyah.getIndexAyah(backStackEntry)
            val viewModel: WithAyahViewModel = hiltNavGraphViewModel(backStackEntry)
            viewModel.setSurahAyah(numSurah, indexAyah)
            WithAyahScreen(viewModel)
        }
        composable(
            route = NavSearch.route
        ) { backStackEntry ->
            val viewModel: SearchViewModel = hiltNavGraphViewModel(backStackEntry)
            SearchScreen(viewModel) { entity ->
                navController.navigate(
                    route = NavAyah.ayahWith(
                        numSurah = entity.numberSurah,
                        indexAyah = entity.numberInSurah - 1
                    )
                )
            }
        }
    }
    var isShowUnimplement by remember { mutableStateOf(false) }
    var isJumpToAyah by remember { mutableStateOf(false) }

    SysUI()
    Scaffold(
        topBar = {
            QuranAppBar(
                backgroundColor = appBarColor,
                modifier = Modifier.statusBarsPadding().fillMaxWidth(),
                onSearchClick = { navController.navigate(NavSearch.route) },
                onJumpToClick = { isJumpToAyah = true },
                onMoreClick = { isShowUnimplement = true }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        content = { NavHost(navController, navGraph) }
    )
    FunctionalityNotAvailablePopup(isShowUnimplement) { isShowUnimplement = false }

    JumpToAyah(isJumpToAyah, { isJumpToAyah = false }) { numSurah, numAyah ->
        isJumpToAyah = false
        navController.navigate(NavAyah.ayahWith(numSurah, numAyah - 1))
    }
}

@Composable
fun QuranAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onJumpToClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_logo),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(R.drawable.ic_text_logo),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.padding(start = 4.dp).heightIn(max = 24.dp)
                )
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(onSearchClick) {
                    Icon(Icons.Default.Search, stringResource(R.string.search))
                }
                IconButton(onJumpToClick) {
                    Icon(Icons.Default.LowPriority, stringResource(R.string.goTo))
                }
                IconButton(onMoreClick) {
                    Icon(Icons.Default.MoreVert, null)
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun SysUI() {
    val uiController = rememberSystemUiController()
    val useDarkIcon = MaterialTheme.colors.isLight
    SideEffect {
        uiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcon
        )
    }
}
