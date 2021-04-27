package id.ratabb.quran.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.ratabb.quran.R
import id.ratabb.quran.ui.ayah.WithAyahScreen
import id.ratabb.quran.ui.common.FunctionalityNotAvailablePopup
import id.ratabb.quran.ui.surah.SurahInfoListScreen

// region Navigation Route
const val WITH_AYAH = "withAyah/"
const val NUM_SURAH = "numSurah"
const val ROUTE_SURAH = "surah"
const val ROUTE_WITH_AYAH = "$WITH_AYAH{$NUM_SURAH}"

// endregion

@Composable
fun QuranAppUI() {
    val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)
    val navController = rememberNavController()
    SysUI()
    Surface(color = MaterialTheme.colors.background) {
        Column {
            QuranAppBar(appBarColor, Modifier.fillMaxWidth())
            NavHost(navController, startDestination = ROUTE_SURAH) {
                composable(ROUTE_SURAH) {
                    SurahInfoListScreen(
                        navController = navController,
                        vm = hiltNavGraphViewModel()
                    )
                }
                composable(
                    ROUTE_WITH_AYAH,
                    arguments = listOf(
                        navArgument(NUM_SURAH) {
                            type = NavType.IntType
                            defaultValue = 1
                        }
                    )
                ) { backStackEntry ->
                    WithAyahScreen(
                        numberSurah = backStackEntry.arguments?.getInt(NUM_SURAH) ?: 1,
                        vm = hiltNavGraphViewModel()
                    )
                }
            }
        }
    }
}

@Composable
fun QuranAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_icon_logo),
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(R.drawable.ic_text_logo),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .heightIn(max = 24.dp)
                )
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { setShowDialog(true) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
                IconButton(
                    onClick = { setShowDialog(true) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
    FunctionalityNotAvailablePopup(showDialog) { setShowDialog(false) }
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
