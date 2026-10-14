//
// Created by Manuel von Au on 04.10.26.
//

import Foundation

enum AppRoute: Hashable, Identifiable {
    case dashboard
    case runningCalculator
    case documentScanner
    case unknown

    var id: Self {
        self
    }
}
