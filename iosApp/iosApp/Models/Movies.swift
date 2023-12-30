//
//  Movies.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct MoviesList {
    let page: Int
    let results: [MovieItem] = []
    let totalPages: Int
    let totalResults: Int
}

struct MovieItem{
    let id: Int
    let title: String
    let originalTitle: String
    let overview: String
    let backdropPath: String
    let posterPath: String
    let voteAverage: Double
    let voteCount: Int
    let popularity: Double
    let releaseDate: String
}
