//
// Created by Manuel von Au on 20.09.26.
//

import Foundation
import SwiftUI
import SharedLogic

struct RunningRoot: View {
    @State private var viewModel = RunningViewModel()
    @State private var states = RunningStates(
        distance: "10",
        time: Time(
            hour: "",
            minute: "",
            second: ""
        ),
        pace: Pace(
            minute: "",
            second: ""
        ),
        speed: "",
        popularDistances: PopularDistances(
            nullPointFourKm: "",
            oneKm: "",
            fiveKm: "",
            tenKm: "",
            twentyOneKm: ""
        )
    )



    var body: some View {
        RunningView(
            state: states,
            onAction: { viewModel.onAction(action: $0) }
        )
            .task {
                for await newState in viewModel.runningStates {
                    states = newState
                }
            }
        .onDisappear {
            viewModel.onDispose()
        }
    }
}
