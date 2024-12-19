package com.example.materialsettingsscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.materialsettingsscreen.ui.theme.BackgroundLight
import com.example.materialsettingsscreen.ui.theme.GreenPrimary
import com.example.materialsettingsscreen.ui.theme.MaterialSettingsScreenTheme

val settingsList = listOf(
    SettingItem(
        title = R.string.notification_title,
        type = SettingItemType.TEXT_ARROW
    ),
    SettingItem(
        title = R.string.show_fajr_imsak_syuruk,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.show_dhuha_prayer_time,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.use_dark_mode,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = false,
    ),
    SettingItem(
        title = R.string.use_24h_time_format,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
)
val aboutAppList = listOf(
    SettingItem(
        title = R.string.app_info,
        type = SettingItemType.TEXT
    ),
    SettingItem(
        title = R.string.privacy_policy,
        type = SettingItemType.TEXT
    )
)

val notificationSettings = listOf(
    SettingItem(
        title = R.string.upcoming_prayer_time,
        type = SettingItemType.TEXT_EXPANDED,
        duration = R.string.five_mins_before,
        description = R.string.notify_before_prayer_starts,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.prayer_time_ends,
        type = SettingItemType.TEXT_EXPANDED,
        duration = R.string.ten_mins_before,
        description = R.string.notify_before_prayer_ends,
        isChecked = false,
    ),
)
val playerTimeSettings = listOf(
    SettingItem(
        title = R.string.notification_type,
        type = SettingItemType.TEXT_ARROW
    ),
    SettingItem(
        title = R.string.subuh,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.dhuha,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.dhuhr,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = false,
    ),
    SettingItem(
        title = R.string.asr,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = false,
    ),
    SettingItem(
        title = R.string.maghrib,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    ),
    SettingItem(
        title = R.string.isha,
        type = SettingItemType.TEXT_SWITCH,
        isChecked = true,
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialSettingsScreenTheme {
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes title: Int,
    onIconClicked: () -> Unit,
    isDoneVisible: Boolean = false,
    onDoneClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onIconClicked) {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f),
            text = stringResource(title),
            style = MaterialSettingsScreenTheme.typography.headlineSmall,
        )

        if (isDoneVisible) {
            TextButton(onDoneClicked) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    color = GreenPrimary,
                    text = stringResource(R.string.done).uppercase(),
                    style = MaterialSettingsScreenTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Composable
fun SettingItem(
    item: SettingItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    var isChecked by remember { mutableStateOf(item.isChecked) }
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
            ) {
                Text(
                    text = stringResource(item.title),
                    style = MaterialSettingsScreenTheme.typography.bodyLarge
                )
                if (item.type == SettingItemType.TEXT_EXPANDED) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = stringResource(
                            item.description ?: R.string.notify_before_prayer_starts
                        ),
                        style = MaterialSettingsScreenTheme.typography.bodySmall
                    )
                }
            }

            when (item.type) {
                SettingItemType.TEXT -> {}
                SettingItemType.TEXT_ARROW -> {
                    IconButton(modifier = Modifier.padding(0.dp), onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }

                SettingItemType.TEXT_SWITCH -> {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = SwitchDefaults.colors(
                            uncheckedTrackColor = BackgroundLight,
                            uncheckedThumbColor = GreenPrimary,
                            uncheckedBorderColor = GreenPrimary,
                            checkedTrackColor = GreenPrimary
                        )
                    )
                }

                SettingItemType.TEXT_EXPANDED -> {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = SwitchDefaults.colors(
                            uncheckedTrackColor = BackgroundLight,
                            uncheckedThumbColor = GreenPrimary,
                            uncheckedBorderColor = GreenPrimary,
                            checkedTrackColor = GreenPrimary
                        )
                    )
                }
            }
        }
        if (item.type == SettingItemType.TEXT_EXPANDED && isChecked) {
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.duration),
                    modifier = Modifier.weight(1f),
                    style = MaterialSettingsScreenTheme.typography.bodyMedium
                )
                Text(
                    text = stringResource(item.duration ?: R.string.fifteen_mins_before),
                    style = MaterialSettingsScreenTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun RadioButtonItem(
    @StringRes title: Int,
    isSelected: Boolean,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(title),
            style = MaterialSettingsScreenTheme.typography.bodyLarge
        )

        RadioButton(
            selected = isSelected,
            onClick = { onSelected(title) },
            colors = RadioButtonDefaults.colors(selectedColor = GreenPrimary)
        )
    }
}

@Composable
fun SettingsSection(
    @StringRes title: Int,
    list: List<SettingItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialSettingsScreenTheme.typography.labelSmall
        )
        Column {
            for (i in list) {
                SettingItem(item = i, onClick = {})
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun RadioButtonsSection(
    options: List<Int>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        options.forEach { option ->
            RadioButtonItem(
                title = option,
                isSelected = (selectedOption == option),
                onSelected = onOptionSelected
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf(R.string.fifteen_mins_before) }
    val options =
        listOf(R.string.fifteen_mins_before, R.string.ten_mins_before, R.string.five_mins_before)

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.notify_before_prayer_starts),
                    style = MaterialSettingsScreenTheme.typography.titleMedium
                )

                RadioButtonsSection(
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            title = R.string.settings_title,
            onIconClicked = {}
        )
        Spacer(Modifier.height(8.dp))
        SettingsSection(
            title = R.string.settings_title,
            list = settingsList,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SettingsSection(
            title = R.string.about_app,
            list = aboutAppList,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            icon = Icons.Default.Close,
            title = R.string.notification_title,
            isDoneVisible = true,
            onIconClicked = {}
        )
        Spacer(Modifier.height(8.dp))
        SettingsSection(
            title = R.string.configurable_reminder,
            list = notificationSettings,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        SettingsSection(
            title = R.string.player_time_notification,
            list = playerTimeSettings,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun TopSettingsBarPreview() {
    MaterialSettingsScreenTheme {
        TopBar(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            title = R.string.settings_title,
            onIconClicked = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun TopNotificationBarPreview() {
    MaterialSettingsScreenTheme {
        TopBar(
            icon = Icons.Default.Close,
            title = R.string.notification_title,
            isDoneVisible = true,
            onDoneClicked = {},
            onIconClicked = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingItemPreview() {
    MaterialSettingsScreenTheme {
        SettingItem(
            item = SettingItem(R.string.app_info, type = SettingItemType.TEXT),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingItemArrowPreview() {
    MaterialSettingsScreenTheme {
        SettingItem(
            item = SettingItem(R.string.notification_title, type = SettingItemType.TEXT_ARROW),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingItemSwitchPreview() {
    MaterialSettingsScreenTheme {
        SettingItem(
            item = SettingItem(R.string.show_fajr_imsak_syuruk, type = SettingItemType.TEXT_SWITCH),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingItemRadioPreview() {
    MaterialSettingsScreenTheme {
        RadioButtonItem(title = R.string.fifteen_mins_before, isSelected = true, onSelected = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingItemExpandedPreview() {
    MaterialSettingsScreenTheme {
        SettingItem(
            item = SettingItem(
                R.string.upcoming_prayer_time,
                description = R.string.notify_before_prayer_starts,
                type = SettingItemType.TEXT_EXPANDED,
                isChecked = true
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingsSectionPreview() {
    MaterialSettingsScreenTheme {
        SettingsSection(
            title = R.string.settings_title,
            list = settingsList
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun RadioButtonsSectionPreview() {
    MaterialSettingsScreenTheme {
        var selectedOption by remember { mutableStateOf(R.string.fifteen_mins_before) }
        val options =
            listOf(
                R.string.fifteen_mins_before,
                R.string.ten_mins_before,
                R.string.five_mins_before
            )
        RadioButtonsSection(
            options = options,
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
fun SettingsBottomSheetPreview() {
    MaterialSettingsScreenTheme {
        SettingsBottomSheet()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun NotificationScreenPreview() {
    MaterialSettingsScreenTheme {
        NotificationScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F9F1)
@Composable
private fun SettingsScreenPreview() {
    MaterialSettingsScreenTheme {
        SettingsScreen()
    }
}

enum class SettingItemType {
    TEXT,
    TEXT_ARROW,
    TEXT_SWITCH,
    TEXT_EXPANDED
}

data class SettingItem(
    @StringRes val title: Int,
    val type: SettingItemType,
    @StringRes val duration: Int? = null,
    @StringRes val description: Int? = null,
    val isChecked: Boolean = false,
    val onCheckedChange: ((Boolean) -> Unit)? = null
)
