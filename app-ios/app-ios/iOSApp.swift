import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        DiHelper().doInitKoin()
    }

    var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
