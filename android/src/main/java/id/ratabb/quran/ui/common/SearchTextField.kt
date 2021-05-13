package id.ratabb.quran.ui.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val view = LocalView.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        singleLine = true,
        maxLines = 1,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(value)
                view.clearFocus()
            }
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}
