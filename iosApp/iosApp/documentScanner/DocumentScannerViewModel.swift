//
// Created by Manuel von Au on 14.10.26.
//

import Foundation
import SwiftUI
import VisionKit

struct DocumentScannerState {
    var scannedImages: [UIImage] = []
    var isShowingScanner: Bool = false
    var errorMessage: String? = nil

    var isScannerSupported: Bool {
        VNDocumentCameraViewController.isSupported
    }
}

@MainActor
final class DocumentScannerViewModel: ObservableObject {
    @Published var state = DocumentScannerState()

    func startScanning() {
        state.errorMessage = nil
        state.isShowingScanner = true
    }

    func finishScanning(with images: [UIImage]) {
        state.isShowingScanner = false
        state.scannedImages.append(contentsOf: images)
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
        state.errorMessage = nil
    }
}
