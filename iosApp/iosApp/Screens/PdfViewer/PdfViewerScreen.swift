//
//  PdfViewerScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import KMMViewModelCore
import KMMViewModelSwiftUI


struct PdfViewerScreenView: View {
    
    @StateViewModel var viewModel = FileDownloadViewModel()
    
    var body: some View {
        VStack {
            switch(viewModel.uiState) {
            case is FileUiState.Loading:
                LoadingView()
            case let successState as FileUiState.Success:
                let pdfData: String = (successState as FileUiState.Success).base64EncodedPdfData
                if let data = Data(base64Encoded: pdfData) {
                    PDFViewer(data: data).edgesIgnoringSafeArea(.all)
                }
            case is FileUiState.Error:
                ErrorView()
            default:
                ErrorView()
            }
        }.onAppear {
            viewModel.downloadPdfFile()
        }
    }
}
