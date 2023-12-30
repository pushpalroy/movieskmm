import Foundation
import SwiftUI
import MultiPlatformLibrary

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			MoviesAppView()
		}
	}
}
