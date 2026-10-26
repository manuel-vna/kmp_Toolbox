package com.jumparoundcreations.toolbox.documentScanner

import android.content.ContentValues
import android.net.Uri
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.provider.MediaStore
import androidx.activity.ComponentActivity

class SaveResultOnDeviceUseCase {
    fun execute(
        activity: ComponentActivity,
        pdfUri: Uri?
    ) {

        if (pdfUri == null) return

        val contentResolver = activity.contentResolver
        val fileName = "Scan_${System.currentTimeMillis()}.pdf"

        // Prepare metadata for the file
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, DIRECTORY_DOWNLOADS)
        }

        // Insert the record into MediaStore
        val destinationUri = contentResolver.insert(
            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
            contentValues
        )

        // Copy the file content from the ML Kit URI to the Downloads URI
        destinationUri?.let { dest ->
            contentResolver.openOutputStream(dest)?.use { outputStream ->
                contentResolver.openInputStream(pdfUri)?.use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            println("File saved successfully to Downloads: $fileName")
        }
    }
}