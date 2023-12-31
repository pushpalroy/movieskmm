//
//  NowPlayingScreen.swift
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


struct NowPlayingScreenView: View {
    
    @StateViewModel var viewModel = NowPlayingViewModel()
    
    var body: some View {
        ScrollView {
            VStack {
                switch(viewModel.uiState) {
                case is NowPlayingUiState.Loading:
                    LoadingView()
                case let successState as NowPlayingUiState.Success:
                    let moviesData = (successState as NowPlayingUiState.Success).moviesList
                    MoviesListView(moviesData: moviesData)
                case is NowPlayingUiState.Error:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.fetchNowPlayingMovies()
            }
        }
        .tabItem {
            Image(systemName: "popcorn.fill")
            Text("Now Playing")
        }
    }
}
