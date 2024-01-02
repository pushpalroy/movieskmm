//
//  MovieDetailsScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 01/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import KMMViewModelCore
import KMMViewModelSwiftUI


struct MovieDetailsScreenView: View {
    
    @StateViewModel var viewModel = MovieDetailsViewModel()
    var movieId: Int32
    
    var body: some View {
        VStack {
            switch(viewModel.uiState) {
            case is MovieDetailsUiState.Loading:
                LoadingView()
            case let successState as MovieDetailsUiState.Success:
                let moviesDetailsData = (successState as MovieDetailsUiState.Success).movieDetails
                MovieDetailsView(movieDetails: moviesDetailsData)
            case is MovieDetailsUiState.Error:
                ErrorView()
            default:
                ErrorView()
            }
        }.onAppear {
            viewModel.fetchMovieDetails(movieId: movieId)
        }
    }
}

struct MovieDetailsView: View {
    
    var movieDetails: MultiPlatformLibrary.MovieDetails
    
    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(url: URL(string: "https://image.tmdb.org/t/p/w342" + movieDetails.backdropPath)) { image in
                image.resizable().aspectRatio(contentMode: .fit)
            } placeholder: {
                ProgressView()
            }
            .frame(height: 250)
            
            Text(movieDetails.title)
                .font(.title2)
                .lineLimit(2)
                .multilineTextAlignment(.leading)
                .padding(.horizontal, 16)
            
            Text(movieDetails.tagline)
                .font(.subheadline)
                .foregroundColor(Color.gray)
                .bold()
                .lineLimit(2)
                .multilineTextAlignment(.leading)
                .padding(.horizontal, 16)
                .padding(.top, 4)
            
            HStack(alignment: .center) {
                LabelValueView(label: "Vote", value: String(format: "%.2f", movieDetails.voteAverage))
                LabelValueView(label: "Popularity", value: String(format: "%.2f", movieDetails.popularity))
                LabelValueView(label: "Budget", value: String(format: "%.2f", movieDetails.budget))
            }
            .padding(.top, 12)
            .padding(.horizontal, 16)
            
            Text("Overview")
                .font(.body)
                .bold()
                .padding(.top, 12)
                .padding(.horizontal, 16)
            
            Text(movieDetails.overview)
                .font(.body)
                .padding(.top, 6)
                .padding(.horizontal, 16)
                .multilineTextAlignment(.leading)
            
            Spacer() // Pushes everything to the top
        }
        .padding(.top, 0)
        .padding(.leading, 0)
        .padding(.trailing, 0)
        .navigationBarTitle(movieDetails.title, displayMode: .inline)
    }
}

struct LabelValueView: View {
    
    var label: String
    var value: String
    
    var body: some View {
        VStack(alignment: .trailing) {
            Text(label)
                .font(.caption)
                .bold()
                .padding(.trailing, 16)
            
            Text(value)
                .font(.caption)
                .padding(.trailing, 16)
        }
    }
}
