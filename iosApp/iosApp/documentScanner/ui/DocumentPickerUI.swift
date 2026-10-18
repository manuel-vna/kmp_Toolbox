//
// Created by Manuel von Au on 17.10.26.
//

import SwiftUI

struct DocumentExportPicker: UIViewControllerRepresentable {
    var url: URL

    func makeUIViewController(context: Context) -> UIDocumentPickerViewController {
        let picker = UIDocumentPickerViewController(forExporting: [url], asCopy: true)
        return picker
    }

    func updateUIViewController(_ uiViewController: UIDocumentPickerViewController, context: Context) {
    }
}