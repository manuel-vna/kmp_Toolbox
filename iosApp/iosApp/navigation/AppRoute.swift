//
// Created by Manuel von Au on 04.10.26.
//

import Foundation

enum AppRoute: Hashable, Identifiable {
    case dashboard
    case paceCalculator
    case unknown

    var id: Self {
        self
    }
}
