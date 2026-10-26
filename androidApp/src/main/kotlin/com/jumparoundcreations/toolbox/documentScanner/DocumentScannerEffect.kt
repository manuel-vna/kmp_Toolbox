package com.jumparoundcreations.toolbox.documentScanner

sealed class DocumentScannerEffect {
    data class ShowSnackbar(val message: String) : DocumentScannerEffect()
}
