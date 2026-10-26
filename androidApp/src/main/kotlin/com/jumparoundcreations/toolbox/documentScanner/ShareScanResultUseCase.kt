package com.jumparoundcreations.toolbox.documentScanner

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.core.content.FileProvider
import java.io.File

class ShareScanResultUseCase {
    fun execute(
        activity: ComponentActivity,
        pdfUri: Uri?
    ) {
        if (pdfUri == null) return

        val file = if (pdfUri.scheme == "file") {
            File(pdfUri.path ?: return)
        } else {
            File(pdfUri.toString().removePrefix("file://"))
        }

        val contentUri = FileProvider.getUriForFile(
            activity,
            "${activity.packageName}.fileprovider",
            file
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        activity.startActivity(Intent.createChooser(shareIntent, "Share scanned PDF"))
    }
}
