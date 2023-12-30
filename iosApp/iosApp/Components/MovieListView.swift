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
                ImageCardView(
                    movieItem: MovieItem(
                        id: Int(item.id),
                        title: item.title,
                        originalTitle: item.originalTitle,
                        overview: item.overview,
                        backdropPath: item.backdropPath,
                        posterPath: item.posterPath,
                        voteAverage: item.voteAverage,
                        voteCount: Int(item.id),
                        popularity: item.popularity,
                        releaseDate: item.releaseDate
                    )
                )
            }
        }.padding(10)
    }
}
