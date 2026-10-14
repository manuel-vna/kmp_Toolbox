//
// Created by Manuel von Au on 12.10.26.
//

import Foundation
import SwiftUI
import VisionKit

struct DocumentScanner: View {
    @State private var scannedImages: [UIImage] = []
    @State private var isShowingScanner = false
    @State private var errorMessage: String? = nil

    private var isScannerSupported: Bool {
        VNDocumentCameraViewController.isSupported
    }

    var body: some View {
        VStack(spacing: 20) {
            if !isScannerSupported {
                VStack(spacing: 12) {
                    Image(systemName: "camera.badge.ellipsis")
                        .font(.system(size: 48))
                        .foregroundStyle(.secondary)

                    Text("Document Scanning Not Supported")
                        .font(.headline)

                    Text("VisionKit document camera scanning requires a physical iOS device with a camera. Please test on an iPhone or iPad.")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                        .multilineTextAlignment(.center)
                        .padding(.horizontal)
                }
                .padding()
                .background(RoundedRectangle(cornerRadius: 16).fill(Color(uiColor: .systemGroupedBackground)))
            } else {
                Button(action: {
                    isShowingScanner = true
                }) {
                    Label("Scan Document", systemImage: "doc.viewfinder")
                        .font(.headline)
                        .padding()
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
            }

            if let error = errorMessage {
                Text(error)
                    .font(.footnote)
                    .foregroundStyle(.red)
            }

            if !scannedImages.isEmpty {
                HStack {
                    Text("Scanned Pages (\(scannedImages.count))")
                        .font(.headline)

                    Spacer()

                    Button("Clear All", role: .destructive) {
                        scannedImages.removeAll()
                    }
                    .font(.subheadline)
                }

                ScrollView(.vertical, showsIndicators: true) {
                    LazyVGrid(columns: [GridItem(.adaptive(minimum: 140), spacing: 16)], spacing: 16) {
                        ForEach(Array(scannedImages.enumerated()), id: \.offset) { index, image in
                            VStack(spacing: 8) {
                                Image(uiImage: image)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(maxHeight: 180)
                                    .cornerRadius(8)
                                    .shadow(radius: 2)

                                Text("Page \(index + 1)")
                                    .font(.caption)
                                    .foregroundStyle(.secondary)
                            }
                            .padding(8)
                            .background(RoundedRectangle(cornerRadius: 12).fill(Color(uiColor: .secondarySystemGroupedBackground)))
                        }
                    }
                }
            } else if isScannerSupported {
                Spacer()
                VStack(spacing: 8) {
                    Image(systemName: "doc.text.viewfinder")
                        .font(.system(size: 40))
                        .foregroundStyle(.tertiary)
                    Text("No pages scanned yet")
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
                Spacer()
            }
        }
        .padding()
        .navigationTitle("Document Scanner")
        .sheet(isPresented: $isShowingScanner) {
            DocumentCameraView(
                onFinish: { images in
                    self.isShowingScanner = false
                    self.scannedImages.append(contentsOf: images)
                },
                onCancel: {
                    self.isShowingScanner = false
                },
                onError: { error in
                    self.isShowingScanner = false
                    self.errorMessage = "Scanner error: \(error.localizedDescription)"
                }
            )
        }
    }
}

struct DocumentScanner_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            DocumentScanner()
        }
    }
}
