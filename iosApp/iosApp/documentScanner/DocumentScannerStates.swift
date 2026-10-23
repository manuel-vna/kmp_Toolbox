//
//  DocumentScannerStates.swift
//  iosApp
//
//  Created by Manuel von Au on 18.10.26.
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
