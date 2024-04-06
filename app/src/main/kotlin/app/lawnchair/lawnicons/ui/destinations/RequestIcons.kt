package app.lawnchair.lawnicons.ui.destinations

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.lawnchair.lawnicons.ui.components.core.LawniconsScaffold
import app.lawnchair.lawnicons.ui.components.core.SimpleListRow
import app.lawnchair.lawnicons.viewmodel.RequestIconsViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RequestIcons(
    onBack: () -> Unit,
    isExpandedScreen: Boolean,
    requestIconsViewModel: RequestIconsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val requestedIcons = requestIconsViewModel.requestedIcons.collectAsState()

    val list = requestedIcons.value?.list ?: listOf()
    val iconCount = requestedIcons.value?.iconCount ?: 0

    val request = buildForm(list.joinToString("%0A") { it.component })

    LaunchedEffect(key1 = requestedIcons) {
        requestIconsViewModel.getRequestedIcons()
    }

    LawniconsScaffold(
        title = "Request icons",
        onBack = onBack,
        isExpandedScreen = isExpandedScreen
    ) {
        LazyColumn(contentPadding = it) {
            item {
                Card(
                    onClick = {
                        val website = Uri.parse(request)
                        val intent = Intent(Intent.ACTION_VIEW, website)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                    ),
                ) {
                    SimpleListRow(
                        label = "You have $iconCount apps with no icons",
                        description = "Tap to request all unthemed icons",
                    )
                }
            }
            stickyHeader {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Apps with no icons",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 32.dp, top = 12.dp),
                    )
                }
            }
            itemsIndexed(list) { index, it ->
                SimpleListRow(
                    label = it.name,
                    description = it.component,
                    background = true,
                    first = index == 0,
                    last = index == list.lastIndex,
                    divider = index != list.lastIndex,
                )
            }
        }
    }
}

private fun buildForm(string: String): String {
    return "https://docs.google.com/forms/d/e/1FAIpQLSe8ItNYse9f4z2aT1QgXkKeueVTucRdUYNhUpys5ShHPyRijg/viewform?entry.1759726669=$string"
}
