package com.jumparoundcreations.toolbox.documentScanner.ui

import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jumparoundcreations.toolbox.documentScanner.DocumentScannerIntent
import com.jumparoundcreations.toolbox.documentScanner.DocumentScannerState
import com.jumparoundcreations.toolbox.documentScanner.DocumentScannerViewModel

@Composable
fun ColumnScope.ScanResultScreen(
    activity: ComponentActivity?,
    state: DocumentScannerState,
    viewModel: DocumentScannerViewModel
) {

    if (!state.errorMessage.isNullOrEmpty()) {
        Text(
            text = state.errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    if (state.scannedPageUris.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Scanned Pages (${state.scannedPageUris.size})",
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(onClick = {
                viewModel.onIntent(DocumentScannerIntent.ClearAll)
            }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text("Clear All")
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.scannedPageUris) { uri ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                ) {
                    val bitmap = rememberBitmapFromUri(uri)
                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap,
                            contentDescription = "Scanned page",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                if (activity != null) {
                    viewModel.onIntent(DocumentScannerIntent.ShareScanResult(activity))
                } else {
                    println("Share error")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Share")
        }

        Button(
            onClick = {
                if (activity != null) {
                    viewModel.onIntent(DocumentScannerIntent.SaveResultOnDevice(activity))
                } else {
                    println("Saving error")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Default.Save,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Save on device")
        }

    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No pages scanned at the moment",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }


}

@Composable
fun rememberBitmapFromUri(uri: Uri): ImageBitmap? {
    val context = LocalContext.current
    var bitmap by remember(uri) { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(uri) {
        try {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            val decodedBitmap = ImageDecoder.decodeBitmap(source)
            bitmap = decodedBitmap.asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return bitmap
}