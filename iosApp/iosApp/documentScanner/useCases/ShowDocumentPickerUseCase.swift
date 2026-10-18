//
// Created by Manuel von Au on 17.10.26.
//

import Foundation
import SwiftUI

struct ShowDocumentPickerUseCase {

    func execute(context: Context, url: URL) {
        func makeUIViewController(context: Context) -> UIDocumentPickerViewController {
            let picker = UIDocumentPickerViewController(forExporting: [url], asCopy: true)
            return picker
        }

        func updateUIViewController(_ uiViewController: UIDocumentPickerViewController, context: Context) {
        }
    }

}