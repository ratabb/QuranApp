package id.ratabb.quran.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = AppColors,
        shapes = AppShapes,
        typography = AppType,
        content = content
    )
}
