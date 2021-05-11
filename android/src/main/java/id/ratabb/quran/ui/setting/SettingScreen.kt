package id.ratabb.quran.ui.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import id.ratabb.quran.R
import id.ratabb.quran.ui.theme.DayNight

@Composable
fun SettingScreen() {
    val vm: SettingViewModel = hiltNavGraphViewModel()
    val state = vm.dayNight.observeAsState(DayNight.FOLLOW_SYSTEM)
    SettingContent(state.value, vm::setDayNight)
}

@Composable
fun SettingContent(
    dayNight: DayNight,
    onSetDayNight: (DayNight) -> Unit
) {
    var expandMenu by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Theme")
        Divider()
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { expandMenu = true }
            ) {
                val stringRes = dayNight.toStringRes()
                Icon(
                    imageVector = dayNight.toIcon(),
                    contentDescription = stringResource(stringRes)
                )
                Text(
                    text = stringResource(stringRes),
                    modifier = Modifier.padding(horizontal = 8.dp).weight(1F)
                )
            }
            DropdownMenu(
                expanded = expandMenu,
                onDismissRequest = { expandMenu = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                DayNight.values().forEach { dn ->
                    DropdownMenuItem(
                        onClick = {
                            expandMenu = false
                            onSetDayNight(dn)
                        }

                    ) {
                        val stringRes = dn.toStringRes()
                        Icon(
                            imageVector = dn.toIcon(),
                            contentDescription = stringResource(stringRes)
                        )
                        Text(
                            text = stringResource(stringRes),
                            modifier = Modifier.padding(horizontal = 8.dp).weight(1F)
                        )
                    }
                }
            }
        }
    }
}

private fun DayNight.toIcon(): ImageVector = when (this) {
    DayNight.LIGHT -> Icons.Default.BrightnessHigh
    DayNight.DARK -> Icons.Default.Brightness4
    DayNight.FOLLOW_SYSTEM -> Icons.Default.BrightnessAuto
}

private fun DayNight.toStringRes(): Int = when (this) {
    DayNight.LIGHT -> R.string.light
    DayNight.DARK -> R.string.dark
    DayNight.FOLLOW_SYSTEM -> R.string.follow_system
}
