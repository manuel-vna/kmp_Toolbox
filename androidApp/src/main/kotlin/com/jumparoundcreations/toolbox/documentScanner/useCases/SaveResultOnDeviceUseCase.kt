package com.jumparoundcreations.toolbox.documentScanner.useCases

import android.content.ContentValues
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SaveResultOnDeviceUseCase {
    fun execute(
        activity: ComponentActivity,
        pdfUri: Uri?
    ): Boolean {
        if (pdfUri == null) return false

        val contentResolver = activity.contentResolver
        val currentDateTime =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
        val fileName = "${currentDateTime}_documentScan.pdf"

        // Prepare metadata for the file
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }

        // Insert the record into MediaStore
        val destinationUri = contentResolver.insert(
            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
            contentValues
        )

        // Copy the file content from the ML Kit URI to the Downloads URI
        return destinationUri?.let { dest ->
            try {
                contentResolver.openOutputStream(dest)?.use { outputStream ->
                    contentResolver.openInputStream(pdfUri)?.use { inputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                true
            } catch (e: Exception) {
                false
            }
        } ?: false
    }
}
