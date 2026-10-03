//
// Created by Manuel von Au on 20.09.26.
//

import Foundation
import SwiftUI
import SharedLogic

struct PaceCalculatorRoot: View {
    @State private var viewModel = PaceCalculatorViewModel()
    @State private var states = PaceCalculatorStates(
        distanceKm: "10",
        timeHour: "",
        timeMinute: "",
        timeSecond: "",
        paceMinute: "",
        paceSecond: "",
        speedKmPerHour: "",
        popularOneKm: "",
        popularFiveKm: "",
        popularTenKm: "",
        popularTwentyOneKm: ""
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
