//
// Created by Manuel von Au on 12.10.26.
//

import Foundation
import SwiftUI
import VisionKit

struct DocumentScanner: View {
    @StateObject private var viewModel = DocumentScannerViewModel()

    var body: some View {
        VStack(spacing: 20) {
            if !viewModel.state.isScannerSupported {
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
                    viewModel.startScanning()
                }) {
                    Label("Scan Document", systemImage: "doc.viewfinder")
                        .font(.headline)
                        .padding()
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
            }

            if let error = viewModel.state.errorMessage {
                Text(error)
                    .font(.footnote)
                    .foregroundStyle(.red)
            }

            if !viewModel.state.scannedImages.isEmpty {
                HStack {
                    Text("Scanned Pages (\(viewModel.state.scannedImages.count))")
                        .font(.headline)

                    Spacer()

                    Button("Clear All", role: .destructive) {
                        viewModel.clearAllScans()
                    }
                    .font(.subheadline)
                }

                ScrollView(.vertical, showsIndicators: true) {
                    LazyVGrid(columns: [GridItem(.adaptive(minimum: 140), spacing: 16)], spacing: 16) {
                        ForEach(Array(viewModel.state.scannedImages.enumerated()), id: \.offset) { index, image in
                            VStack(spacing: 8) {
                                ZStack(alignment: .topTrailing) {
                                    Image(uiImage: image)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(maxHeight: 180)
                                        .cornerRadius(8)
                                        .shadow(radius: 2)

                                    if viewModel.state.selectedIndices.contains(index) {
                                        Image(systemName: "checkmark.circle.fill")
                                            .font(.system(size: 24))
                                            .foregroundColor(.green)
                                            .background(Circle().fill(Color.white))
                                            .padding(6)
                                    }
                                }
                                .onTapGesture {
                                    viewModel.toggleSelection(for: index)
                                }

                                Text("Page \(index + 1)")
                                    .font(.caption)
                                    .foregroundStyle(.secondary)
                            }
                            .padding(8)
                            .background(RoundedRectangle(cornerRadius: 12).fill(Color(uiColor: .secondarySystemGroupedBackground)))
                        }
                    }
                }

                if !viewModel.state.selectedIndices.isEmpty {
                    Button(action: {
                        _ = viewModel.generatePDFFromSelectedImages()
                        viewModel.state.isShowingExportOptions = true
                    }) {
                        Label("Save as PDF (\(viewModel.state.selectedIndices.count) selected)", systemImage: "doc.badge.arrow.down")
                            .font(.headline)
                            .padding()
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(.borderedProminent)
                    .tint(.green)
                }
            } else if viewModel.state.isScannerSupported {
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
        .sheet(isPresented: $viewModel.state.isShowingScanner) {
            DocumentCameraView(
                onFinish: { images in
                    viewModel.finishScanning(with: images)
                },
                onCancel: {
                    viewModel.cancelScanning()
                },
                onError: { error in
                    viewModel.failScanning(with: error)
                }
            )
        }
        .confirmationDialog("Export PDF", isPresented: $viewModel.state.isShowingExportOptions, titleVisibility: .visible) {
            Button("Share") {
                viewModel.state.isShowingShareSheet = true
            }
            Button("Browse Files") {
                viewModel.state.isShowingDocumentPicker = true
            }
            Button("Cancel", role: .cancel) {
            }
        } message: {
            Text("Where to save?")
        }
        .sheet(isPresented: $viewModel.state.isShowingShareSheet) {
            if let url = viewModel.state.generatedPDFURL {
                ActivityViewController(activityItems: [url])
            }
        }
        .sheet(isPresented: $viewModel.state.isShowingDocumentPicker) {
            if let url = viewModel.state.generatedPDFURL {
                DocumentExportPicker(url: url)
            }
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
