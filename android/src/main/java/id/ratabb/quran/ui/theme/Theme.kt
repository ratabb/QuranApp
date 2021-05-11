package id.ratabb.quran.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import id.ratabb.quran.ui.setting.SettingViewModel

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val dayNight = hiltNavGraphViewModel<SettingViewModel>()
        .dayNight.observeAsState(DayNight.FOLLOW_SYSTEM).value
    val isDark = dayNight.isDark(isSystemInDarkTheme())
    MaterialTheme(
        colors = if (isDark) AppDarkColors else AppLightColors,
        shapes = AppShapes,
        typography = AppType,
        content = content
    )
}
