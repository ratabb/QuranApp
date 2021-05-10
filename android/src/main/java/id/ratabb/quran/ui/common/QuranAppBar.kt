package id.ratabb.quran.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LowPriority
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.ratabb.quran.R

@Composable
fun QuranAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onJumpToClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    val uiController = rememberSystemUiController()
    val useDarkIcon = MaterialTheme.colors.isLight
    SideEffect {
        uiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcon
        )
    }
    TopAppBar(
        modifier = modifier,
        backgroundColor = backgroundColor,
        title = {
            Row {
                Icon(painterResource(R.drawable.ic_icon_logo), null)
                Icon(
                    painter = painterResource(R.drawable.ic_text_logo),
                    contentDescription = stringResource(R.string.app_name),
                    modifier = Modifier.padding(start = 4.dp).heightIn(max = 24.dp)
                )
            }
        },
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
        }
    )
}
