//
// Created by Manuel von Au on 20.09.26.
//

import Foundation
import SharedLogic
import SwiftUI

struct PaceCalculatorView: View {
    let state: PaceCalculatorStates
    let onAction: (PaceCalculatorIntents) -> Void

    var body: some View {
        Form {

            Section("Distance") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            TextField("hh", text: Binding(
                                get: { state.distance == 0 ? "" : String(state.distance) },
                                set: { newValue in
                                    if let newDistance = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newDistance))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("km").foregroundStyle(.secondary)

                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }

            Section("Time") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            TextField("hh", text: Binding(
                                get: { state.distance == 0 ? "" : String(state.distance) },
                                set: { newValue in
                                    if let newDistance = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newDistance))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                        GridRow {
                            TextField("mm", text: Binding(
                                get: { state.distance == 0 ? "" : String(state.distance) },
                                set: { newValue in
                                    if let newDistance = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newDistance))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                        GridRow {
                            TextField("ss", text: Binding(
                                get: { state.distance == 0 ? "" : String(state.distance) },
                                set: { newValue in
                                    if let newDistance = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newDistance))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("h").foregroundStyle(.secondary)
                        Text("m").foregroundStyle(.secondary)
                        Text("s").foregroundStyle(.secondary)
                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }

            Section("Pace") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            TextField("hh", text: Binding(
                                get: { state.pace == 0 ? "" : String(state.pace) },
                                set: { newValue in
                                    if let newPace = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangePace(newPace: newPace))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("min/km").foregroundStyle(.secondary)

                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }

            Section("Speed") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            TextField("hh", text: Binding(
                                get: { state.speed == 0 ? "" : String(state.speed) },
                                set: { newValue in
                                    if let newSpeed = Double(newValue) {
                                        onAction(PaceCalculatorIntents.ChangeSpeed(newSpeed: newSpeed))
                                    }
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("km/h").foregroundStyle(.secondary)

                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }

        }
    }
}


struct PaceCalculatorView_Previews: PreviewProvider {
    static var previews: some View {
        PaceCalculatorView(
            state: PaceCalculatorStates(
                distance: 1.0,
                timeHour: 2.0,
                timeMinute: 3.0,
                timeSecond: 4.0,
                timeTotalInMinutes: 5.0,
                pace: 6.0,
                speed: 7.0
            ),
            onAction: { _ in }
        )
    }
}
