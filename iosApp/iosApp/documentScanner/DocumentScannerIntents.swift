//
//  DocumentScannerIntents.swift
//  iosApp
//
//  Created by Manuel von Au on 18.10.26.
//

import Foundation
import SwiftUI

enum DocumentScannerIntent {
    case startScanning
    case finishScanning(images: [UIImage])
    case cancelScanning
    case failScanning(error: Error)
    case clearAllScans
    case toggleSelection(index: Int)
    case generatePDF
}
