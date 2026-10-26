package com.jumparoundcreations.toolbox.documentScanner

import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DocumentScannerViewModel(
    private val getScanResultUseCase: GetScanResultUseCase = GetScanResultUseCase(),
    private val shareScanResultUseCase: ShareScanResultUseCase = ShareScanResultUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow(DocumentScannerState())
    val state: StateFlow<DocumentScannerState> = _state.asStateFlow()

    private val _launchScannerEvent = MutableSharedFlow<IntentSenderRequest>()
    val launchScannerEvent: SharedFlow<IntentSenderRequest> = _launchScannerEvent.asSharedFlow()

    fun onIntent(intent: DocumentScannerIntent) {
        when (intent) {
            is DocumentScannerIntent.StartScanning -> {
                getScanResultUseCase.getScanIntent(
                    activity = intent.activity,
                    onSuccess = { intentSender ->
                        viewModelScope.launch {
                            val request = IntentSenderRequest.Builder(intentSender).build()
                            _launchScannerEvent.emit(request)
                        }
                    },
                    onFailure = { e ->
                        _state.update { it.copy(errorMessage = "Scanner failed: ${e.localizedMessage}") }
                    }
                )
            }

            is DocumentScannerIntent.HandleScanResult -> {
                val scanResult = getScanResultUseCase.parseResult(intent.data)
                val pageUris = scanResult?.pages?.map { it.imageUri } ?: emptyList()
                _state.update {
                    it.copy(
                        scanResult = scanResult,
                        scannedPageUris = pageUris,
                        errorMessage = null
                    )
                }
            }

            is DocumentScannerIntent.ScanFailed -> {
                _state.update { it.copy(errorMessage = intent.errorMessage) }
            }

            is DocumentScannerIntent.ShareScanResult -> {
                shareScanResultUseCase.execute(
                    intent.activity,
                    pdfUri = _state.value.scanResult?.pdf?.uri
                )
            }

            is DocumentScannerIntent.SaveResultOnDevice -> {
                // ToDo
            }

            is DocumentScannerIntent.ClearAll -> {
                _state.update {
                    it.copy(
                        scannedPageUris = emptyList(),
                        pdfUri = null,
                        errorMessage = null
                    )
                }
            }
        }
    }
}
