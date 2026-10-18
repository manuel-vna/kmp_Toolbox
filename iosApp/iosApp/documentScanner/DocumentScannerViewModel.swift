//
// Created by Manuel von Au on 14.10.26.
//

import Foundation
import SwiftUI
import VisionKit

struct DocumentScannerState {
    var scannedImages: [UIImage] = []
    var selectedIndices: Set<Int> = []
    var isShowingScanner: Bool = false
    var errorMessage: String? = nil
    var isShowingExportOptions: Bool = false
    var generatedPDFURL: URL? = nil
    var isShowingShareSheet: Bool = false
    var isShowingDocumentPicker: Bool = false

    var isScannerSupported: Bool {
        VNDocumentCameraViewController.isSupported
    }
}

@MainActor
final class DocumentScannerViewModel: ObservableObject {
    @Published var state = DocumentScannerState()
    private let generatePDFUseCase = GeneratePDFUseCase()

    func startScanning() {
        state.errorMessage = nil
        state.isShowingScanner = true
    }

    func finishScanning(with images: [UIImage]) {
        state.isShowingScanner = false
        state.scannedImages.append(contentsOf: images)
        // Automatically select newly scanned images or leave selection as is? Let's select all or leave unselected. Let's select newly scanned or keep existing. Actually, selecting new ones or keeping user control is good. Let's not auto-select unless desired, or auto-select all scanned images. Let's select all scanned images by default so users can immediately save as PDF.
        for i in 0..<state.scannedImages.count {
            state.selectedIndices.insert(i)
        }
    }

    func cancelScanning() {
        state.isShowingScanner = false
    }

    func failScanning(with error: Error) {
        state.isShowingScanner = false
        state.errorMessage = "Scanner error: \(error.localizedDescription)"
    }

    func clearAllScans() {
        state.scannedImages.removeAll()
        state.selectedIndices.removeAll()
        state.errorMessage = nil
    }

    func toggleSelection(for index: Int) {
        if state.selectedIndices.contains(index) {
            state.selectedIndices.remove(index)
        } else {
            state.selectedIndices.insert(index)
        }
    }

    func generatePDFFromSelectedImages() -> URL? {
        let selectedImages = state.selectedIndices.sorted().compactMap { index -> UIImage? in
            guard index < state.scannedImages.count else {
                return nil
            }
            return state.scannedImages[index]
        }

        do {
            let url = try generatePDFUseCase.execute(images: selectedImages)
            state.generatedPDFURL = url
            return url
        } catch {
            state.errorMessage = "Failed to generate PDF: \(error.localizedDescription)"
            return nil
        }
    }
}
