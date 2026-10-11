//
// Created by Manuel von Au on 20.09.26.
//

import Foundation
import SharedLogic
import SwiftUI

struct RunningView: View {
    let state: RunningStates
    let onAction: (RunningIntents) -> Void

    @FocusState private var isInputFocused: Bool

    var body: some View {
        Form {

            Section("Distance") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            TextField("km", text: Binding(
                                get: { String(state.distance) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangeDistance(newDistance: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.numbersAndPunctuation)
                            .focused($isInputFocused)
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
                                get: { String(state.time.hour) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangeTimeHour(newTimeHour: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)
                        }
                        GridRow {
                            TextField("mm", text: Binding(
                                get: { state.time.minute },
                                set: { newValue in
                                    onAction(RunningIntents.ChangeTimeMinute(newTimeMinute: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)
                        }
                        GridRow {
                            TextField("ss", text: Binding(
                                get: { String(state.time.second) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangeTimeSecond(newTimeSecond: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)
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
                            TextField("mm", text: Binding(
                                get: { String(state.pace.minute) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangePaceMinute(newPaceMinute: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)

                            TextField("ss", text: Binding(
                                get: { String(state.pace.second) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangePaceSecond(newPaceSecond: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)
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
                                get: { String(state.speed) },
                                set: { newValue in
                                    onAction(RunningIntents.ChangeSpeed(newSpeed: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                            .focused($isInputFocused)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("km/h").foregroundStyle(.secondary)

                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
            }

            Section("Popular distances") {
                ZStack(alignment: .topLeading) {
                    Grid(horizontalSpacing: 8, verticalSpacing: 12) {
                        GridRow {
                            Text(state.popularDistances.nullPointFourKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularDistances.oneKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularDistances.fiveKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularDistances.tenKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularDistances.twentyOneKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
                        Text("0.4 km").foregroundStyle(.white).padding(.trailing, 12)
                        Text("1 km").foregroundStyle(.white).padding(.trailing, 12)
                        Text("5 km").foregroundStyle(.white).padding(.trailing, 12)
                        Text("10 km").foregroundStyle(.white).padding(.trailing, 12)
                        Text("21,097 km").foregroundStyle(.white).padding(.trailing, 12)
                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                }
                .padding(.vertical, 4)
                .background(RoundedRectangle(cornerRadius: 12).fill(Color.black.opacity(0.8)))
                .clipShape(RoundedRectangle(cornerRadius: 12))
            }

        }
        .scrollDismissesKeyboard(.interactively)
        .toolbar {
            ToolbarItemGroup(placement: .keyboard) {
                Spacer()
                Button("Done") {
                    isInputFocused = false
                }
            }
        }
    }
}


struct PaceCalculatorView_Previews: PreviewProvider {
    static var previews: some View {
        RunningView(
            state: RunningStates(
                distance: "1",
                time: Time(
                    hour: "2",
                    minute: "3",
                    second: "4"
                ),
                pace: Pace(
                    minute: "5",
                    second: "6"
                ),
                speed: "7",
                popularDistances: PopularDistances(
                    nullPointFourKm: "8",
                    oneKm: "9",
                    fiveKm: "10",
                    tenKm: "11",
                    twentyOneKm: "12"
                )
            ),
            onAction: { _ in }
        )
    }
}
