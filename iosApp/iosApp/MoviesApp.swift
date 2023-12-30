import SwiftUI
import mokoMvvmFlowSwiftUI
import MultiPlatformLibrary

struct MoviesAppView: View {
    var body: some View {
        TabView {
            NowPlayingScreenView()
            TopRatedScreenView()
        }
    }
}
