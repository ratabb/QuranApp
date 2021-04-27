package id.ratabb.quran.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) AppDarkColors else AppLightColors,
        shapes = AppShapes,
        typography = AppType,
        content = content
    )
}
