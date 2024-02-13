import ComposeApp
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin(koinInitialization: { _ in })
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
