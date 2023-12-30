//
//  ImageCardView.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ImageCardView: View {
    var movieItem: MovieItem
    
    var body: some View {
        VStack {
            AsyncImage(url: URL(string: "https://image.tmdb.org/t/p/w342" + movieItem.posterPath)) { image in
                image.resizable().aspectRatio(contentMode: .fill)
            } placeholder: {
                ProgressView()
            }
            .frame(height: 250)
            .overlay(RoundedRectangle(cornerRadius: 10)
                .stroke(Color.gray, lineWidth: 0.5))
        }
        .background(Color.white)
        .cornerRadius(10)
        .shadow(radius: 5)
    }
}
