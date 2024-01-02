//
//  PdfViewer.swift
//  iosApp
//
//  Created by Pushpal Roy on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import PDFKit

struct PDFViewer: UIViewRepresentable {
    let data: Data
    
    func makeUIView(context: Context) -> PDFView {
        // Create a PDFView and set its properties
        let pdfView = PDFView()
        pdfView.autoScales = true // Automatically scale the PDF to fit the view
        return pdfView
    }
    
    func updateUIView(_ uiView: PDFView, context: Context) {
        // Load the PDF document from the given data
        if let document = PDFDocument(data: data) {
            uiView.document = document
        }
    }
}
