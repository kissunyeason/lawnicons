package app.lawnchair.lawnicons.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.lawnchair.lawnicons.ui.components.core.SimpleListRow
import app.lawnchair.lawnicons.ui.util.PreviewLawnicons

@PreviewLawnicons
@Composable
fun RequestIconsCard(
    request: String = "",
    iconCount: Int = 0,
) {
    val context = LocalContext.current

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
