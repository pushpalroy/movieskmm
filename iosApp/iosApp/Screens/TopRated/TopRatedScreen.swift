//
//  TopRatedScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import MultiPlatformLibrary
import KMMViewModelCore
import KMMViewModelSwiftUI

struct TopRatedScreenView: View {
    
    @StateViewModel var viewModel = TopRatedViewModel()
    
    var body: some View {
        ScrollView {
            VStack {
                switch(viewModel.uiState) {
                case is TopRatedUiState.Loading:
                    LoadingView()
                case let successState as TopRatedUiState.Success:
                    let moviesData = (successState as TopRatedUiState.Success).moviesList
                    MoviesListView(moviesData: moviesData)
                case is TopRatedUiState.Error:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.fetchTopRatedMovies()
            }
        }
        .tabItem {
            Image(systemName: "chart.line.uptrend.xyaxis")
            Text("Top Rated")
        }
    }
}
