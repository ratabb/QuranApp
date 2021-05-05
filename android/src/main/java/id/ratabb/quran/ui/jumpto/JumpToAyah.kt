package id.ratabb.quran.ui.jumpto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LowPriority
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import id.ratabb.quran.R
import id.ratabb.quran.ui.common.SimpleTextButton
import items.entity.SurahEntity

@Composable
fun JumpToAyah(
    isShow: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit
) {
    val vm: JumpToAyahViewModel = hiltNavGraphViewModel()
    val data: List<SurahEntity> = vm.data.observeAsState(emptyList()).value

    var indexSelected: Int by remember { mutableStateOf(0) }
    var numAyahField: TextFieldValue by remember { mutableStateOf(TextFieldValue()) }

    val doConfirm: () -> Unit = {
        onConfirm(
            data[indexSelected].number,
            numAyahField.runCatching { text.toInt() }.getOrElse { 1 } //
        )
    }

    if (isShow) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { JumpToAyahTitle() },
            text = {
                JumpToAyahContent(
                    data = data,
                    indexSelected = indexSelected,
                    numAyahField = numAyahField,
                    onImeAction = doConfirm
                ) { index, fieldValue ->
                    indexSelected = index
                    numAyahField = fieldValue
                }
            },
            confirmButton = { SimpleTextButton(android.R.string.ok, doConfirm) },
            dismissButton = { SimpleTextButton(android.R.string.cancel, onDismiss) }
        )
    }
}

@Composable
private fun JumpToAyahContent(
    data: List<SurahEntity>,
    indexSelected: Int,
    numAyahField: TextFieldValue,
    onImeAction: () -> Unit,
    onChange: (Int, TextFieldValue) -> Unit
) {
    val surahNames: (Int) -> String = { index: Int -> data[index].run { "$number. $textIndo" } }
    val ayahRange: () -> String = { "1--${data[indexSelected].numberOfVerses}" }

    var expandedSurahNames: Boolean by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        Box {
            Text(
                text = surahNames(indexSelected),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth().clickable { expandedSurahNames = true }
            )
            DropdownMenu(
                expanded = expandedSurahNames,
                onDismissRequest = { expandedSurahNames = false },
                modifier = Modifier.heightIn(max = 200.dp) //
            ) {
                for (index in data.indices) {
                    DropdownMenuItem(
                        onClick = {
                            expandedSurahNames = false
                            onChange(index, TextFieldValue())
                        }
                    ) {
                        val isSelected = index == indexSelected
                        Text(
                            text = surahNames(index),
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = if (isSelected) FontWeight.Bold
                            else FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth().weight(1F)
                        )
                        if (isSelected) Icon(Icons.Default.Done, null)
                    }
                }
            }
        }
        Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = stringResource(R.string.input_num_ayah_in_range, ayahRange()),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = numAyahField,
            onValueChange = { onChange(indexSelected, it) },
            placeholder = { Text(ayahRange()) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onImeAction() })
        )
    }
}

@Composable
private fun JumpToAyahTitle() {
    Row {
        Icon(Icons.Default.LowPriority, stringResource(R.string.goTo))
        Text(
            text = stringResource(R.string.goTo),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
