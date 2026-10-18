//
// Created by Manuel von Au on 17.10.26.
//

import Foundation
import UIKit

struct GeneratePDFUseCase {
    func execute(images: [UIImage]) throws -> URL {
        guard !images.isEmpty else {
            throw NSError(domain: "GeneratePDFUseCase", code: 1, userInfo: [NSLocalizedDescriptionKey: "No images provided for PDF generation."])
        }

        let pdfRenderer = UIGraphicsPDFRenderer(bounds: CGRect(x: 0, y: 0, width: 612, height: 792))
        let outputURL = FileManager.default.temporaryDirectory.appendingPathComponent("ScannedDocument-\(UUID().uuidString).pdf")

        try pdfRenderer.writePDF(to: outputURL) { context in
            for image in images {
                context.beginPage()
                let pageSize = CGSize(width: 612, height: 792)
                let imageSize = image.size
                guard imageSize.width > 0, imageSize.height > 0 else {
                    continue
                }
                let aspectRatio = imageSize.width / imageSize.height

                var drawWidth = pageSize.width - 40
                var drawHeight = drawWidth / aspectRatio
                if drawHeight > pageSize.height - 40 {
                    drawHeight = pageSize.height - 40
                    drawWidth = drawHeight * aspectRatio
                }
                let x = (pageSize.width - drawWidth) / 2
                let y = (pageSize.height - drawHeight) / 2

                image.draw(in: CGRect(x: x, y: y, width: drawWidth, height: drawHeight))
            }
        }
        return outputURL
    }
}
