//
//  NowPlayingScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import mokoMvvmFlowSwiftUI
import MultiPlatformLibrary

struct NowPlayingScreenView: View {
    
    @ObservedObject
    var viewModel: NowPlayingViewModel = KoinHelper().getNowPlayingViewModel()
    
    @State
    var uiState: NowPlayingUiState = NowPlayingUiStateUninitialized()
    
    var body: some View {
        
        let appUiState = viewModel.uiState
        
        ScrollView {
            VStack {
                switch(uiState) {
                case is NowPlayingUiStateLoading:
                    LoadingView()
                case let successState as NowPlayingUiStateSuccess:
                    let moviesData = (successState as NowPlayingUiStateSuccess).moviesList
                    MoviesListView(moviesData: moviesData)
                case is NowPlayingUiStateError:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.fetchNowPlayingMovies()
                appUiState.subscribe { state in
                    self.uiState = state!
                }
            }
        }
        .tabItem {
            Image(systemName: "popcorn.fill")
            Text("Now Playing")
        }
    }
}
