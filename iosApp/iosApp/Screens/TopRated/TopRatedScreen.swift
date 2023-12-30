//
//  TopRatedScreen.swift
//  iosApp
//
//  Created by Pushpal Roy on 30/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import mokoMvvmFlowSwiftUI
import MultiPlatformLibrary

struct TopRatedScreenView: View {
    
    @ObservedObject
    var viewModel: TopRatedViewModel = KoinHelper().getTopRatedViewModel()
    
    @State
    var uiState: TopRatedUiState = TopRatedUiStateUninitialized()
    
    var body: some View {
        
        let appUiState = viewModel.uiState
        
        ScrollView {
            VStack {
                switch(uiState) {
                case is TopRatedUiStateLoading:
                    LoadingView()
                case let successState as TopRatedUiStateSuccess:
                    let moviesData = (successState as TopRatedUiStateSuccess).moviesList
                    MoviesListView(moviesData: moviesData)
                case is TopRatedUiStateError:
                    ErrorView()
                default:
                    ErrorView()
                }
            }.onAppear {
                viewModel.fetchTopRatedMovies()
                appUiState.subscribe { state in
                    self.uiState = state!
                }
            }
        }
        .tabItem {
            Image(systemName: "chart.line.uptrend.xyaxis")
            Text("Top Rated")
        }
    }
}
