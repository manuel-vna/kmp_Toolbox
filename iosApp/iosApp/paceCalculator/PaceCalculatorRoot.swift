//
// Created by Manuel von Au on 20.09.26.
//

import Foundation
import SwiftUI
import SharedLogic

struct PaceCalculatorRoot: View {
    @State private var viewModel = PaceCalculatorViewModel()
    @State private var states = PaceCalculatorStates(
        distance: 0.0,
        timeHour: 0.0,
        timeMinute: 0.0,
        timeSecond: 0.0,
        timeTotalInMinutes: 0.0,
        pace: 0.0,
        speed: 0.0
    )


    var body: some View {
        PaceCalculatorView(
            state: states,
            onAction: { viewModel.onAction(action: $0) }
        )
            .task {
                for await newState in viewModel.paceCalculatorStates {
                    states = newState
                }
            }
        .onDisappear {
            viewModel.onDispose()
        }
    }
}
