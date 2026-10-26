package com.jumparoundcreations.toolbox.documentScanner

import android.content.Intent
import androidx.activity.ComponentActivity

sealed class DocumentScannerIntent {
    data class StartScanning(val activity: ComponentActivity) : DocumentScannerIntent()
    data class HandleScanResult(val data: Intent?) : DocumentScannerIntent()
    data class ScanFailed(val errorMessage: String) : DocumentScannerIntent()
    data class ShareScanResult(val activity: ComponentActivity) : DocumentScannerIntent()
    data class SaveResultOnDevice(val activity: ComponentActivity) : DocumentScannerIntent()
    object ClearAll : DocumentScannerIntent()
}
