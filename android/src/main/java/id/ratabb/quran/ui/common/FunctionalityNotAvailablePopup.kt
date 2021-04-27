package id.ratabb.quran.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun FunctionalityNotAvailablePopup(
    isShow: Boolean,
    onDismiss: () -> Unit
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = onDismiss,
            text = {
                Text(
                    text = "Functionality not available \uD83D\uDE48",
                    style = MaterialTheme.typography.body2
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(android.R.string.ok))
                }
            }
        )
    }
}
