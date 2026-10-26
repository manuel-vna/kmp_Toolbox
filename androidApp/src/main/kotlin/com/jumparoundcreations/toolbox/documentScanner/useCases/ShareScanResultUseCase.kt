package com.jumparoundcreations.toolbox.documentScanner.useCases

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.core.content.FileProvider
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ShareScanResultUseCase {
    fun execute(
        activity: ComponentActivity,
        pdfUri: Uri?
    ) {
        if (pdfUri == null) return

        val currentDateTime =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
        val fileName = "${currentDateTime}_documentScan.pdf"

        val sourceFile = if (pdfUri.scheme == ContentResolver.SCHEME_FILE) {
            File(pdfUri.path ?: return)
        } else {
            File(pdfUri.toString().removePrefix("file://"))
        }

        val targetFile = File(activity.cacheDir, fileName)
        try {
            sourceFile.copyTo(targetFile, overwrite = true)
        } catch (e: Exception) {
            // fallback to sourceFile if copy fails
        }

        val fileToShare = if (targetFile.exists()) targetFile else sourceFile

        val contentUri = FileProvider.getUriForFile(
            activity,
            "${activity.packageName}.fileprovider",
            fileToShare
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        activity.startActivity(Intent.createChooser(shareIntent, "Share scanned PDF"))
    }
}
