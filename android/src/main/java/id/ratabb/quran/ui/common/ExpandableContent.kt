package id.ratabb.quran.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@OptIn(ExperimentalAnimationApi::class)
@JvmOverloads
@Composable
fun ExpandableContent(
    title: String,
    initialEnpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    val (isEnpanded, setEnpanded) = remember { mutableStateOf(initialEnpanded) }

    val iconRotationDegree = if (isEnpanded) 180F else 0F

    Column {
        Row(
            modifier = Modifier.clickable { setEnpanded(!isEnpanded) }
        ) {
            Text(title)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Icon arrow for expandable content.",
                modifier = Modifier.rotate(iconRotationDegree)
            )
        }
        AnimatedVisibility(isEnpanded) { content() }
    }
}
