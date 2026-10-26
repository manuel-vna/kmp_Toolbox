package com.jumparoundcreations.toolbox.documentScanner

import android.content.Intent
import android.content.IntentSender
import androidx.activity.ComponentActivity
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

class GetScanResultUseCase {
    fun getScanIntent(
        activity: ComponentActivity,
        onSuccess: (IntentSender) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val options =
            GmsDocumentScannerOptions.Builder()
                .setGalleryImportAllowed(false)
                .setPageLimit(5)
                .setResultFormats(RESULT_FORMAT_PDF)
                .setScannerMode(SCANNER_MODE_FULL)
                .build()

        val scanner = GmsDocumentScanning.getClient(options)

        scanner.getStartScanIntent(activity)
            .addOnSuccessListener { intentSender ->
                onSuccess(intentSender)
            }
            .addOnFailureListener { e ->
                onFailure(e)
            }
    }

    fun parseResult(data: Intent?): GmsDocumentScanningResult? {
        return GmsDocumentScanningResult.fromActivityResultIntent(data)
    }
}
