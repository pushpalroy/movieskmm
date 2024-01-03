//
//  MovieListView.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import MultiPlatformLibrary

struct MoviesListView: View {
    var moviesData: MultiPlatformLibrary.MoviesList
    var body: some View {
        LazyVGrid(
            columns: [
                GridItem(.flexible()),
                GridItem(.flexible())
            ],
            spacing: 20
        ) {
            ForEach(moviesData.results, id: \.id) { item in
                VStack(alignment: .leading){
                    ImageCardView(movieItem: item)
                    Text(item.title)
                        .font(.caption)
                        .lineLimit(1)
                        .bold()
                        .padding(.top, 4)
                }
            }
        }.padding(10)
    }
}

struct FavMoviesListView: View {
    var moviesData: [MovieItem]
    var body: some View {
        LazyVGrid(
            columns: [
                GridItem(.flexible()),
                GridItem(.flexible())
            ],
            spacing: 20
        ) {
            ForEach(moviesData, id: \.id) { item in
                VStack(alignment: .leading){
                    ImageCardView(movieItem: item)
                    Text(item.title)
                        .font(.caption)
                        .lineLimit(1)
                        .bold()
                        .padding(.top, 4)
                }
            }
        }.padding(10)
    }
}
