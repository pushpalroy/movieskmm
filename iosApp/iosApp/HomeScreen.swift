import SwiftUI
import mokoMvvmFlowSwiftUI
import MultiPlatformLibrary

struct HomeScreen: View {
    
    @ObservedObject
    var viewModel: NowPlayingViewModel = KoinHelper().getNowPlayingViewModel()
    
    @State var uiState: NowPlayingUiState = NowPlayingUiStateUninitialized()
    
    var body: some View {
        
        let appUiState = viewModel.uiState
        
        VStack {
            switch(uiState) {
            case is NowPlayingUiStateUninitialized:
                Button("Fetch Now Playing Movies") {
                    viewModel.fetchNowPlayingMovies()
                }
            case is NowPlayingUiStateLoading:
                LoadingView()
            case let successState as NowPlayingUiStateSuccess:
                SuccessView()
            case is NowPlayingUiStateError:
                ErrorView()
            default:
                ErrorView()
            }
        }.onAppear {
            appUiState.subscribe { state in
                self.uiState = state!
            }
        }
    }
}

struct SuccessView: View {
    var body: some View {
        Text("Success")
    }
}

struct LoadingView: View {
    var body: some View {
        ProgressView().padding()
    }
}

struct ErrorView: View {
    var body: some View {
        Text("An error occurred. Please try again.")
            .foregroundColor(.red)
            .padding()
    }
}
