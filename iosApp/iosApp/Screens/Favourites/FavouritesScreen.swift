//
//  FavouritesScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 03/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import KMMViewModelCore
import KMMViewModelSwiftUI


struct FavouritesScreenView: View {
    
    @StateViewModel var viewModel = FavMoviesViewModel()
    
    var body: some View {
        ScrollView {
            VStack {
                switch(viewModel.uiState) {
                case is FavMoviesUiState.Loading:
                    LoadingView()
                case let successState as FavMoviesUiState.Success:
                    let moviesData = (successState as FavMoviesUiState.Success).moviesList
                    FavMoviesListView(moviesData: moviesData)
                case is FavMoviesUiState.Error:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.getAllFavouriteMovies_()
            }
        }
        .tabItem {
            Image(systemName: "heart.fill")
            Text("Favourites")
        }
    }
}
