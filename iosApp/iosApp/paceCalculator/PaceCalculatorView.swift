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
                            TextField("km", text: Binding(
                                get: { String(state.distanceKm) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newValue)
                                    )
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.numbersAndPunctuation)
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
                                get: { String(state.timeHour) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangeTimeHour(newTimeHour: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                        }
                        GridRow {
                            TextField("mm", text: Binding(
                                get: { String(state.timeMinute) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangeTimeMinute(newTimeMinute: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
                        }
                        GridRow {
                            TextField("ss", text: Binding(
                                get: { String(state.timeSecond) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangeTimeSecond(newTimeSecond: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
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
                                get: { String(state.paceMinute) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangePaceMinute(newPaceMinute: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)

                            TextField("ss", text: Binding(
                                get: { String(state.paceSecond) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangePaceSecond(newPaceSecond: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
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
                                get: { String(state.speedKmPerHour) },
                                set: { newValue in
                                    onAction(PaceCalculatorIntents.ChangeSpeed(newSpeed: newValue))
                                }
                            ))
                            .multilineTextAlignment(.center)
                            .frame(width: 80)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .keyboardType(.decimalPad)
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
                            Text(state.popularFiveKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularTenKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                        GridRow {
                            Text(state.popularTwentyOneKm)
                                .multilineTextAlignment(.center)
                                .frame(width: 80)
                                .frame(maxWidth: .infinity, alignment: .center)
                                .foregroundStyle(.pink)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .center)

                    VStack(alignment: .trailing, spacing: 12) {
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
    }
}


struct PaceCalculatorView_Previews: PreviewProvider {
    static var previews: some View {
        PaceCalculatorView(
            state: PaceCalculatorStates(
                distanceKm: "1",
                timeHour: "2",
                timeMinute: "3",
                timeSecond: "4",
                paceMinute: "6",
                paceSecond: "7",
                speedKmPerHour: "8",
                popularOneKm: "9",
                popularFiveKm: "10",
                popularTenKm: "11",
                popularTwentyOneKm: "12"
            ),
            onAction: { _ in }
        )
    }
}

