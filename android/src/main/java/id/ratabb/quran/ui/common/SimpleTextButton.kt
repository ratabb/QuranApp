package id.ratabb.quran.ui.common

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun SimpleTextButton(@StringRes textId: Int, onClick: () -> Unit) {
    TextButton(onClick) { Text(stringResource(textId)) }
}
