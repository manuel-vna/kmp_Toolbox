import SwiftUI
import SharedLogic

struct Navigation: View {
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            Dashboard()
                .navigationDestination(for: AppRoute.self) { route in
                    switch route {
                    case .dashboard:
                        Dashboard()
                    case .paceCalculator:
                        PaceCalculatorRoot()
                    case .unknown:
                        Unknown()
                    }
                }
        }
    }
}

struct Navigation_Previews: PreviewProvider {
    static var previews: some View {
        Navigation()
    }
}
