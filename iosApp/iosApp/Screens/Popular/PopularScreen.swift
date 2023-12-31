//
//  PopularScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 31/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import MultiPlatformLibrary
import KMMViewModelCore
import KMMViewModelSwiftUI


struct PopularScreenView: View {
    
    @StateViewModel var viewModel = PopularViewModel()
    
    var body: some View {
        ScrollView {
            VStack {
                switch(viewModel.uiState) {
                case is PopularUiState.Loading:
                    LoadingView()
                case let successState as PopularUiState.Success:
                    let moviesData = (successState as PopularUiState.Success).moviesList
                    MoviesListView(moviesData: moviesData)
                case is PopularUiState.Error:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.fetchPopularMovies()
            }
        }
        .tabItem {
            Image(systemName: "star.fill")
            Text("Popular")
        }
    }
}
