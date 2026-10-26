package com.jumparoundcreations.toolbox.documentScanner

import android.net.Uri
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

data class DocumentScannerState(
    val scannedPageUris: List<Uri> = emptyList(),
    val scanResult: GmsDocumentScanningResult? = null,
    val pdfUri: Uri? = null,
    val errorMessage: String? = null
)
