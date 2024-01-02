import SwiftUI
import MultiPlatformLibrary

struct MoviesAppView: View {
    var body: some View {
        NavigationView {
            TabView {
                NowPlayingScreenView()
                TopRatedScreenView()
                PopularScreenView()
            }
        }
    }
}
